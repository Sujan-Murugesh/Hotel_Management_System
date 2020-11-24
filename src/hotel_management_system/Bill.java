package hotel_management_system;

/**
 *
 * @author Sujan
 */
public class Bill extends Main_Window{
    double reservationAmount;
    double tax;
    double discount;
    double res_Advance;
    double Total_Amount;
    double balance_resAmount;
    double subTotal;
    public void reservation_calculate_total(){
        //res_taxes   res_discount  res_advance_amount  //output -res_total_amount
        
        //1.CHECK 3 INPUTS ARE TRUE T D AD  1 1 1
        if(!"".equals(res_taxes.getText()) && !"".equals(res_discount.getText()) && !"".equals(res_advance_amount.getText())){
            tax = Double.parseDouble(res_taxes.getText());
            discount = Double.parseDouble(res_discount.getText());
            res_Advance = Double.parseDouble(res_advance_amount.getText());   
            reservationAmount = Double.parseDouble(res_basic_amount.getText());
            
            subTotal= (reservationAmount + (reservationAmount * tax/100 ));
            
            Total_Amount = (reservationAmount + (reservationAmount * tax/100 )) - (reservationAmount * discount/100);
            balance_resAmount = Total_Amount - res_Advance;
            
            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(balance_resAmount));
        }
        //2.CHECK IF EVERY INPUTS ARE FALSE 0 0 0
        else if("".equals(res_taxes.getText()) && "".equals(res_discount.getText()) && "".equals(res_advance_amount.getText())){       
            res_total_amount.setText(res_basic_amount.getText());
            res_Balance_resAmount.setText(res_basic_amount.getText());
        }
        //3. 0 0 1
        else if("".equals(res_taxes.getText()) && "".equals(res_discount.getText()) && !"".equals(res_advance_amount.getText())){
            
            Total_Amount = Double.parseDouble(res_basic_amount.getText());
            res_Advance = Double.parseDouble(res_advance_amount.getText());
            balance_resAmount = Total_Amount - res_Advance;
            
            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(balance_resAmount));
        }
        // 4. 0 1 0
        else if("".equals(res_taxes.getText()) && !"".equals(res_discount.getText()) && "".equals(res_advance_amount.getText())){
            discount = Double.parseDouble(res_discount.getText());
            reservationAmount = Double.parseDouble(res_basic_amount.getText());
            Total_Amount = reservationAmount - (reservationAmount*discount/100);
            balance_resAmount = Total_Amount;
            
            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(Total_Amount));
        }
        //5. 0 1 1
        else if("".equals(res_taxes.getText()) && !"".equals(res_discount.getText()) && !"".equals(res_advance_amount.getText())){
            discount = Double.parseDouble(res_discount.getText());
            res_Advance = Double.parseDouble(res_advance_amount.getText());   
            reservationAmount = Double.parseDouble(res_basic_amount.getText());
            
            Total_Amount = reservationAmount - (reservationAmount*discount/100);
            balance_resAmount = Total_Amount - res_Advance;
            
            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(balance_resAmount));
        }
        //6. 1 0 0
        else if(!"".equals(res_taxes.getText()) && "".equals(res_discount.getText()) && "".equals(res_advance_amount.getText())){
            tax = Double.parseDouble(res_taxes.getText());
            reservationAmount = Double.parseDouble(res_basic_amount.getText());
            
            Total_Amount = reservationAmount + (reservationAmount*tax/100);
            
            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(Total_Amount));
        }
        //7. 1 0 1
        else if(!"".equals(res_taxes.getText()) && "".equals(res_discount.getText()) && !"".equals(res_advance_amount.getText())){
            tax = Double.parseDouble(res_taxes.getText());
            reservationAmount = Double.parseDouble(res_basic_amount.getText());
            res_Advance = Double.parseDouble(res_advance_amount.getText());
            
            Total_Amount = reservationAmount + (reservationAmount*tax/100);
            balance_resAmount = Total_Amount - res_Advance;
            
            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(balance_resAmount));
        }
        //8. 1 1 0
        else if(!"".equals(res_taxes.getText()) && !"".equals(res_discount.getText()) && "".equals(res_advance_amount.getText())){
            tax = Double.parseDouble(res_taxes.getText());
            reservationAmount = Double.parseDouble(res_basic_amount.getText());
            discount = Double.parseDouble(res_discount.getText());
            
            Total_Amount = (reservationAmount + (reservationAmount*tax/100)) - (reservationAmount*discount/100) ;
            balance_resAmount = Total_Amount;
            
            res_total_amount.setText(String.valueOf(Total_Amount));
            res_Balance_resAmount.setText(String.valueOf(balance_resAmount));
        }
    }
}
