clear all
close all
% SPRAWO ZA 2 TYGODNIE
img = imread('imgnoise.jpg');
imgnonoise = imread('img.jpg');
%imshow(img)
A = [90 7 2 16 8 4 1 4 8 16 2 7 90]
[x y m] = size(img);
newimg = uint8(zeros(x, y, 3));

filter = A' * A;
filter = filter/sum(filter(:));
for k=1:m
    newimg(:, :, k) = conv2(img(:, :, k), filter, 'same');
end

temp = newimg - imgnonoise;
temp = temp.*temp;
tempsum = sum(temp(:));
error = sqrt(tempsum / (x*y*3))
imshow(newimg);
figure;
imshow(newimg-imgnonoise);