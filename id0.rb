require "digest"

f = File.open("movable.sed")
f.seek(0x110)
keyY = f.read(0x10)

sha256 = Digest::SHA256.new

sha256.update(keyY)
hash = sha256.hexdigest.upcase.scan(/.{1,#{2}}/)

id0 = []

for i in 0..3
    id0.push(hash[i*4..i*4+3].reverse)
    id0.flatten!
end

p id0.join