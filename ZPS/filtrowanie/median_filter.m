clear all
close all
% SPRAWO ZA 2 TYGODNIE
img = imread('imgnoise.jpg');
imgnonoise = imread('img.jpg');
%imshow(img)
A = [-1/4 1/2 1 1/2 -1/4]
[x y m] = size(img);
newimg = uint8(zeros(x, y, 3));

window = 31
half_window = floor(window/2)

filter = A' * A;
filter = filter/sum(filter(:));
for i = half_window + 1: x - half_window
    for j = half_window + 1 : y - half_window
        for k = 1 : m
            newimg(i, j, k) = median(reshape(img(i-half_window:i+half_window, j-half_window:j+half_window, k), [1 window*window]));
        end
    end
end

temp = newimg - imgnonoise;
temp = temp.*temp;
tempsum = sum(temp(:));
error = sqrt(tempsum / (x*y*3))
imshow(newimg);
figure;
imshow(newimg-imgnonoise);