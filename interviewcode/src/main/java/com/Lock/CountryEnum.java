package com.Lock;

public enum CountryEnum {

    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"秦"),FOUR(4,"燕"),FIVE(5,"赵"),SIX(6,"魏"),SEVEN(7,"韩");

   private  Integer retCode;
   private  String  retMsg;

    public int getRetCode() {
        return retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    private CountryEnum(Integer retCode, String retMsg){
        this.retCode=retCode;
        this.retMsg=retMsg;
    }

  public static CountryEnum foreach_countryEnum(int index){
      CountryEnum[] values = CountryEnum.values();
      for (CountryEnum element:values) {
          if (index==element.getRetCode()){
              return element;
          }
      }
        return null;
  }



}
