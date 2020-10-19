extern crate byteorder;
extern crate crypto_hash;
extern crate hex;

use byteorder::{ReadBytesExt, LittleEndian};
use crypto_hash::{hex_digest, Algorithm};
use std::fs::File;
use std::io::Read;

fn main() {
    let mut f = File::open("movable.sed").expect("Error: Unable to open");
    let mut buf = Vec::new();
    let _ = f.read_to_end(&mut buf);
    let hash = hex_digest(Algorithm::SHA256, &buf[0x110..0x120]).to_uppercase();
    let bytes = hex::decode(&hash).unwrap();
    for i in 0..4 {
        print!("{:04X}", (&bytes[i*4..i*4+4]).read_u32::<LittleEndian>().unwrap());
    }
}