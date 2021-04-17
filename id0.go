package main

import (
	"bytes"
	"crypto/sha256"
	"encoding/binary"
	"fmt"
	"os"
)

func main() {

	var id0 string
	var id0_array [4]uint32
	var buf = make([]byte, 16)

	fp, err := os.Open("movable.sed")
	if err != nil {
		panic(err)
	}
	defer fp.Close()

	fp.Seek(0x110, 0)
	fp.Read(buf)

	hash := sha256.Sum256(buf)
	binary.Read(bytes.NewReader(hash[:16]), binary.LittleEndian, &id0_array)

	for _, v := range id0_array {
		id0 += fmt.Sprintf("%X", v)
	}
	fmt.Println(id0)
}
