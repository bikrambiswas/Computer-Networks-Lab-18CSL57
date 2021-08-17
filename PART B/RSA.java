import java.util.*;
import java.math.BigInteger;
import java.lang.*;
class RSA
{
public static void main(String[] args)
{
Random rand1=new Random(System.currentTimeMillis());
Random rand2=new Random(System.currentTimeMillis()*10);
int pubkey=2;
BigInteger p=BigInteger.probablePrime(32,rand1);
BigInteger q=BigInteger.probablePrime(32,rand2);
BigInteger n=p.multiply(q);
BigInteger p_1=p.subtract(new BigInteger("1"));
BigInteger q_1=q.subtract(new BigInteger("1"));
BigInteger z=p_1.multiply(q_1);
while(true)
{
BigInteger GCD=z.gcd(new BigInteger(""+pubkey));
if(GCD.equals(BigInteger.ONE))
{
break;
}
pubkey++;
}
BigInteger big_pubkey=new BigInteger(""+pubkey);
BigInteger prvkey=big_pubkey.modInverse(z);
System.out.println("publickey:"+big_pubkey+","+n);
System.out.println("privatekey:"+prvkey+","+n);
Scanner sc=new Scanner(System.in);
System.out.println("enter the message to be encrypted");
String msg=sc.nextLine();
byte[] bytes=msg.getBytes();
for(int i=0;i<msg.length();i++)
{
int asciiVal=bytes[i];
BigInteger val=new BigInteger(""+asciiVal);
BigInteger cipherVal=val.modPow(big_pubkey,n);
System.out.println("Cipher text:"+cipherVal);
BigInteger plain_val=cipherVal.modPow(prvkey,n);
int i_plainVal=plain_val.intValue();
System.out.println("Plain text:"+Character.toString((char)i_plainVal));
}
}
}