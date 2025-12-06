package network.schabel.scratch2python.models;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {

  private String id;
  private BlockType blockType;
  private Block next;
  private Block parent;
  private Map<String, List<Object>> inputs;
  private Map<String, List<Object>> fields;
  private boolean shadow;
  private boolean topLevel;

  public StringBuilder generatePython(List<Block> childNodes, StringBuilder bldr) {
      throw new RuntimeException("Generate Python not implemented!");
  }

}
