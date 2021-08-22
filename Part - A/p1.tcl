set ns [ new Simulator ]
set trf [ open 1.tr w ]
$ns trace-all $trf
set namf [ open 1.nam w ]
$ns namtrace-all $namf
# The below code is used to create the nodes.
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
#This is used to give color to the flow of packets.
$ns color 1 "red"
$ns color 2 "green"
$n0 label "Source/udp0"
$n1 label "Source/udp1"
$n2 label “destination”
#providing the link
$ns duplex-link $n0 $n2 100Kb 100ms DropTail
$ns duplex-link $n1 $n2 20Mb 200ms DropTail
# set the queue size b/w the nodes
$ns set queue-limit $n0 $n2 5
$ns set queue-limit $n1 $n2 5
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0
set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0
set null3 [new Agent/Null]
$ns attach-agent $n2 $null3
set udp1 [new Agent/UDP]
$ns attach-agent $n1 $udp1
set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1
#The below code sets the udp0 packets to red and udp1 #packets to blue color
$udp0 set class_ 1
$udp1 set class_ 2
#The below code is used to connect the agents.
$ns connect $udp0 $null3
$ns connect $udp1 $null3
#The below code is used to set the packet size to 500
$cbr1 set packetSize_ 500Mb
#The below code is used to set the interval of the packets,
$cbr1 set interval_ 0.001
proc finish { } {
global ns namf trf
$ns flush-trace
exec nam 1.nam & 
close $trf
close $namf
exit 0
}
$ns at 0.1 "$cbr0 start"
$ns at 0.1 "$cbr1 start"
$ns at 10.0 "finish"
$ns run
