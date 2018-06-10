clear all;
close all;

I = im2double(imread('wiosna.tiff'));
img = load('test8.jwa', '-mat');

I2 = img.I2;
[x y z] = size(I2);

divider = img.divider;
I2 = double(I2) / double(divider);

I3 = zeros([x y z]);

T = dctmtx(8);

for i=1:1:3
    invdct = @(block_struct) T' * block_struct.data * T;
    I3(:,:,i) = blockproc(I2(:,:,i),[8 8],invdct);
end

I3 = ycbcr2rgb(I3);
imshow(I3-I);

temp = I3 - I;
temp = temp.*temp;
tempsum = sum(temp(:));
error = sqrt(tempsum / (x*y*3))