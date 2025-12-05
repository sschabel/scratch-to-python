package network.schabel.scratch2python;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Block {

  private BlockType blockType;
  private Block next;
  private Block parent;
  private Map<String, List<Object>> inputs;
  private Map<String, List<Object>> fields;
  private boolean shadow;
  private boolean topLevel;

}
