set ns [new Simulator]
set nf [open sample.nam w]
$ns namtrace-all $nf
set tf [open sample.tr w]
$ns trace-all $tf

proc finish { } {
global ns nf tf
$ns flush-trace
close $tf
close $nf
exec nam sample.nam &
exit 0
}

set n0 [$ns node]
set n1 [$ns node]

$ns duplex-link $n0 $n1 2Mb 4ms DropTail

set tcp1 [new Agent/TCP]
$ns attach-agent $n0 $tcp1

set sink [new Agent/TCPSink]
$ns attach-agent $n1 $sink
$ns connect $tcp1 $sink

set ftp [new Application/FTP]
$ftp attach-agent $tcp1

$ns at 0.1 "$ftp start"
$ns at 2.0 "$ftp stop"
$ns at 2.1 "finish"
$ns run
