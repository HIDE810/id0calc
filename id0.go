package main

import (
    "bytes"
    "crypto/sha256"
    "encoding/binary"
    "fmt"
    "os"
    
)

func main() {
    
    var id0 uint32
    var buf = make([]byte, 0x120)
    
    fp, err := os.Open("movable.sed")
    if err != nil {
		panic(err)
	}
    defer fp.Close()
    
    fp.Read(buf)
    
    hash := sha256.Sum256([]byte(buf[0x110:0x120]))
    
    for i := 0; i <= 12; i += 4 {
        binary.Read(bytes.NewReader(hash[i:i+4]), binary.LittleEndian, &id0)
        fmt.Printf("%X", id0)
    }
}