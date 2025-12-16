package network.schabel.scratch2python;

import java.io.File;
import java.io.IOException;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import network.schabel.scratch2python.models.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Slf4j
@ShellComponent
public class CommandHandler {

  protected static final String TARGETS = "targets";
  protected static final String BLOCKS = "blocks";
  protected static final String TOP_LEVEL = "topLevel";
  protected static final String OP_CODE = "opcode";

  @ShellMethod(key = "convert")
  public String parseJson(@ShellOption String path) {
    File jsonFile = new File(path);
    ObjectMapper mapper = new ObjectMapper();
    try {
        StringBuilder bldr = new StringBuilder();
      JsonNode root = mapper.readTree(jsonFile);
      ArrayNode targets = (ArrayNode) root.get(TARGETS);
      for(JsonNode target : targets) {
          if (target.has(BLOCKS)) {
              bldr = parseBlocks(target.get(BLOCKS), bldr);
          }
      }
      return bldr.toString();
    } catch (IOException exception) {
      return String.format("Error reading JSON file provided at %s: " + exception.getMessage(), path);
    }
  }

  protected StringBuilder parseBlocks(JsonNode blocks, StringBuilder bldr) {
    Iterator<String> fields = blocks.fieldNames();

    HashMap<String, JsonNode> topNodes = new HashMap<>();
    HashMap<String, JsonNode> childNodes = new HashMap<>();

    fields.forEachRemaining((field) -> {
      JsonNode node = blocks.get(field);
      if (node != null) {
          log.info("Block " + field + " is not null");
        if (node.get(TOP_LEVEL).asBoolean()) {
          topNodes.put(field, node);
        } else {
          childNodes.put(field, node);
        }
      } else {
          log.info("Block " + field + " is null");
      }
    });

    for (String nodeId : topNodes.keySet()) {
        JsonNode topNode = topNodes.get(nodeId);
        bldr = processNode(nodeId, topNode, childNodes, bldr);
    }

    return bldr;
  }

  protected StringBuilder processNode(String nodeId, JsonNode node, Map<String, JsonNode> childNodes, StringBuilder bldr) {
    Block block = convertToBlock(node);
    block.setId(nodeId);
    List<Block> childBlocks = new ArrayList<>();
    childNodes.keySet().forEach((childNodeId) -> {
        Block childBlock = convertToBlock(childNodes.get(childNodeId));
        childBlock.setId(childNodeId);
        childBlocks.add(childBlock);
    });
    return block.generatePython(0, childBlocks, bldr);
  }

  protected Block convertToBlock(JsonNode node) {
      ObjectMapper mapper = new ObjectMapper();
      BlockType blockType = BlockType.fromDescription((node.get(OP_CODE).asText()));
      log.info("Block " + node.get(OP_CODE).asText() + " being converted...");
      return switch (blockType) {
          case BlockType.CONTROL_FOREVER -> mapper.convertValue(node, ForeverBlock.class);
          case BlockType.CONTROL_IF_ELSE -> mapper.convertValue(node, IfElseBlock.class);
          case BlockType.CONTROL_WAIT -> mapper.convertValue(node, WaitBlock.class);
          case BlockType.DIGITAL_READ ->mapper.convertValue(node, DigitalReadBlock.class);
          case BlockType.DIGITAL_WRITE -> mapper.convertValue(node, DigitalWriteBlock.class);
          case BlockType.MENU_DIGITAL_PINS -> mapper.convertValue(node, MenuDigitalPinsBlock.class);
          case BlockType.MENU_ON_OFF -> mapper.convertValue(node, MenuOnOffBlock.class);
          case BlockType.OPERATOR_EQUALS -> mapper.convertValue(node, EqualsBlock.class);
          default -> throw new RuntimeException("Unknown block type: " + blockType);
      };
  }

}
