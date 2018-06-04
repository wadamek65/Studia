from PIL import Image
im = Image.open("in.jpg")
pix = list(im.getdata())
print(len(pix))
out = open("in.txt", "w")
out.write("".join(str(pix)).replace(", ", "\n"))
out.close()
