package network.schabel.scratch2python;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum BlockType {

  CONTROL_IF_ELSE("control_if_else"),
  CONTROL_FOREVER("control_forever"),
  CONTROL_WAIT("control_wait"),
  DIGITAL_READ("onegpioRpiPico_digital_read"),
  DIGITAL_WRITE("onegpioRpiPico_digital_write"),
  MENU_DIGITAL_PINS("onegpioRpiPico_menu_digital_pins"),
  MENU_ON_OFF("onegpioRpiPico_menu_on_off"),
  OPERATOR_EQUALS("operator_equals");

  private String description;

  BlockType(String description) {
    this.description = description;
  }

}
