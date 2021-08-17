import java.util.Scanner;

public class crc {
	public String crc (String dividend, String divisor){
		
		String str1, div;
		int shift;
		shift = dividend.length( ) - divisor.length( );
		while (shift >= 0)
		{
			//XORing the string
			dividend = Integer.toBinaryString (Integer.parseInt(dividend, 2 ) ^	(Integer.parseInt (divisor, 2) << shift));
			shift = dividend.length( ) - divisor.length( );
		}
		if (dividend.length( ) < 16)
		{
			while (dividend.length( ) != 16)
			{
				dividend = "0" + dividend;
			}
		}
		System.out.println("Div="+dividend);
		return dividend;
	}
	
	
	public static void main (String args[ ])
	{
		String data, checksum, syn, dividend, Received_data;
		int padding;
		String polynomial = "10001000000100001";
		Scanner sc = new Scanner(System.in);
		//Data to be transmitted from sender side
		System.out.println("Enter the data to be encrypted\n");
		data = sc.next( );
		dividend = data;
		padding = polynomial.length( ) - 1;
		//Zero padding of data based on polynomial
		for(int i=0;i < padding; i++)
		{
			dividend += "0";
		}
		crc obj = new crc( );
		checksum = obj.crc(dividend, polynomial);
		//Generated Codeword
		data = data + checksum;
		System.out.println("Sender Checksum="+checksum);
		System.out.println( "Code word transmitted overnetwork="+data);
		//Data received at the receiver side
		System.out.println("Enter the received codeword\n");
		Received_data = sc.next( );
		syn = obj.crc(Received_data,polynomial);
		//Generated Syn bits after checking checksum
		if(Long.parseLong (syn) == 0)
		System.out.println("No error in data transmission");
		else
		System.out.println("Error in transmission");
		
		sc.close();
	} 
}