package network.schabel.scratch2python.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@Slf4j
public class ForeverBlock extends Block {

    public ForeverBlock() {
        super();
    }

    @Override
    public StringBuilder generatePython(int level, List<Block> blocks, StringBuilder bldr) {
        log.info("Generating Python for ForeverBlock...");
        level++;
        bldr = addSpaces(level, bldr);
        bldr.append("while True:").append("\n");
        List<Block> childBlocks = blocks.stream()
                .filter((block ->
                        block.getParentId() != null && block.getParentId().equals(this.getId())))
                .toList();

        for (Block block : childBlocks) {
            bldr = block.generatePython(level, blocks, bldr);
        }
        bldr.append("\n");
        return bldr;
    }
}
