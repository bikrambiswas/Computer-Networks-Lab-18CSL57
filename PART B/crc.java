import java.util.*;
class crc
{
    public String crc (String dividend,String divisor)
    {
        String str,div1;
        int shift; 
        shift = dividend.length()- divisor.length();
        while(shift>=0)
        {
            dividend = Integer.toBinaryString (Integer.parseInt(dividend,2)^(Integer.parseInt(divisor,2)<<shift));
            shift = dividend.length() - divisor.length();
        }
        if(dividend.length()<16)
        {
            while(dividend.length()!=16)
            {
                dividend = "0" + dividend;
            }
        }
        System.out.println("div= "+dividend);
        return dividend;
    }
    public static void main(String args[])
    {
        String data,checksum,syn,Received_data,dividend;
        int padding;
        String polynomial = "1001000000100001";
        
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter the data to be encrypted");
        data = sc.next();
        dividend = data;
        padding = polynomial.length() - 1;
        
        for(int i=0;i<padding;i++)
        {
            dividend+="0";
        }  
        
        crc obj =  new crc();
        checksum = obj.crc(dividend,polynomial);
        data = data + checksum;
        System.out.println("Sender's Checksum= "+checksum);
        System.out.println("Codeword transmitted over network= "+data);
        
        System.out.println("Enter the codeword Received: ");
        Received_data = sc.next();
        syn = obj.crc(Received_data,polynomial);
        
        if((Long.parseLong(syn))==0)
        {
            System.out.println("No error in data transmission:");
        }
        else
        {
            System.out.println("Error in data transmission:");
        }
    }
    
}