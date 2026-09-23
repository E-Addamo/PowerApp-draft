package com.powerapp;

public class SubFund {
    private String isin;
    private float shares;
    
	public SubFund(String isin, float shares) throws InvalidSubFundException{
		if(isin == null || isin.equals("")){
            throw new InvalidSubFundException("ISIN cannot be null or empty.");
        }

        //inserisci un controllo più preciso sul formato ISIN
        if(isin.length() != 12){
            throw new InvalidSubFundException("Invalid ISIN format.");
        }
        
        this.isin = isin;

        if(shares < 0){
            throw new InvalidSubFundException("SubFund's shares cannot be negative.");
        }
		this.shares = shares;
	}

	public String getIsin() {
		return isin;
	}
	public float getShares() {
		return shares;
	}

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        SubFund subFund = (SubFund) o;
        return subFund.getIsin().equals(isin) && subFund.getShares() == shares;
    }
}
