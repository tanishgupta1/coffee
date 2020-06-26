package models.ingredients;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
//@AllArgsConstructor
public class SugarSyrup implements Ingredient {
  private Long quantity;

  public Long getQuantity() {
    if(quantity == null)
      return 0L;
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
}

