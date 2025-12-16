package network.schabel.scratch2python.models;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {

  private String id;
  @JsonProperty(value = "next")
  private String nextId;
  @JsonProperty(value = "parent")
  private String parentId;
  private Map<String, List<Object>> inputs;
  private Map<String, List<Object>> fields;
  private boolean shadow;
  private boolean topLevel;

  public StringBuilder generatePython(int level, List<Block> childNodes, StringBuilder bldr) {
      throw new RuntimeException("Generate Python not implemented!");
  }

  public StringBuilder addSpaces(int level, StringBuilder bldr) {
      for(int spaceCount = 0; spaceCount < level * 2; spaceCount++) {
          bldr.append(" ");
      }
      return bldr;
  }

}
