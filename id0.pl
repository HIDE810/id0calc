#!/usr/bin/perl

use Digest::SHA qw(sha256_hex);

my $buf;
open my $fh, "<", "movable.sed" or die $!;
binmode $fh;
seek $fh, 0x110, 0;
read $fh, $buf, 0x10;
close $fh;
my $hash = sha256_hex($buf);
my @array = $hash =~ /.{8}/g;
for(my $i = 0; $i < 4; $i++){
    print reverse(@array[$i] =~ /.{2}/g)
}