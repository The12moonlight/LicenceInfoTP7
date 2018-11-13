/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelPackage;

/**
 * Correspond à un entité de la table DISCOUT_CODE
 * @author pierr
 */
public class DiscountCodeEntity {
    public String discount_code;
    public float rate;
    
    public DiscountCodeEntity(String discountCode, float rate){
        this.discount_code = discountCode;
        this.rate = rate;
    }
    
    
	/**
	 * Get the value of discount_code
	 *
	 * @return the value of discount_code
	 */
	public String getDiscountCode() {
		return discount_code;
	}

	/**
	 * Get the value of rate
	 *
	 * @return the value of rate
	 */
	public float getRate() {
		return rate;
	}
}
