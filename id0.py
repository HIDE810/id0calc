import hashlib
import re

with open('movable.sed', mode='rb') as f:
    f.seek(0x110)
    keyY = f.read(0x10)

hash = re.split('(..)', hashlib.sha256(keyY).hexdigest().upper())[1::2]

id0 = []

for i in range(4):
    id0.extend(list(reversed(hash[i*4:i*4+4])))

print(''.join(id0))