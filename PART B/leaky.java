import java.util.*;
public class leaky
{
public static void main(String[ ] args)
{
int time, output_rate, max_buffer_size, num_of_pkts, count=0, cur_buffer_size=0;
Scanner in = new Scanner(System.in);
System.out.println("Enter the maximum size of buffer");
max_buffer_size = in.nextInt( );
System.out.println("Enter the output rate of packets from the buffer");
output_rate = in.nextInt( );
System.out.println("Enter the number of arriving packets");
num_of_pkts = in.nextInt( );
int[] pkt_size = new int[num_of_pkts];
int[] arr_time_of_pkts =new int[num_of_pkts];
System.out.println("Enter the time of arrival of packets");
for(count=0;count<num_of_pkts;count++)
{
arr_time_of_pkts[count] = in.nextInt( );
}
time=0;
count=0;
while(count <num_of_pkts)
{
if(time==arr_time_of_pkts[count])
{
Random rn = new Random();
pkt_size[count] = (rn.nextInt(10)+1) * 10;
System.out.println("Packet "+(count+1)+" has arrived & its size is:" +pkt_size[count]);
System.out.println("Current Size of buffer:"+cur_buffer_size);
if (cur_buffer_size + pkt_size [count] <=max_buffer_size)
{
cur_buffer_size += pkt_size[count];
System.out.println("Packet"+(count+1)+" arriving at "+arr_time_of_pkts[count]+ "is CONFORMING PACKET\n");
}
else
{
System.out.println("Packet "+(count+1)+" arriving at "+arr_time_of_pkts[count]+" is NON CONFORMING PACKET asit exceeds the buffer limit\n");
}
count++;
}
time++;
cur_buffer_size -= output_rate;
if(cur_buffer_size< 0)
cur_buffer_size=0;
}
in.close();
}
}