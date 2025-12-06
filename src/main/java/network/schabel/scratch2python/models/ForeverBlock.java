package network.schabel.scratch2python.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForeverBlock extends Block {

    public ForeverBlock() {
        super();
    }

    @Override
    public StringBuilder generatePython(List<Block> blocks, StringBuilder bldr) {
        System.out.println("Generating Python for ForeverBlock...");
        bldr.append("while(True):").append("\n");
        List<Block> childBlocks = blocks.stream()
                .filter((block ->
                        block.getParent() != null && block.getParent().getId().equals(this.getId())))
                .toList();

        for (Block block : childBlocks) {
            bldr = block.generatePython(blocks, bldr);
        }

        return bldr;
    }
}
