package network.schabel.scratch2python;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@ShellComponent
public class CommandHandler {

  protected static final String TARGETS = "targets";
  protected static final String BLOCKS = "blocks";

  @ShellMethod(key = "convert")
  public String parseJson(@ShellOption String path) {
    File jsonFile = new File(path);
    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode root = mapper.readTree(jsonFile);
      ArrayNode targets = (ArrayNode) root.get(TARGETS);
      targets.forEach((target) -> {
        if (target.has(BLOCKS)) {
          parseBlocks(target.get(BLOCKS));
        }
      });
    } catch (IOException exception) {
      return String.format("Error reading JSON file provided at %s: " + exception.getMessage(), path);
    }
    return "File successfully processed.";
  }

  protected void parseBlocks(JsonNode blocks) {
    Iterator<String> fields = blocks.fieldNames();
    System.out.println("Printing all fields within the blocks object...");
    fields.forEachRemaining((field) -> System.out.println(field));
    System.out.println("All fields printed!");
  }

}
