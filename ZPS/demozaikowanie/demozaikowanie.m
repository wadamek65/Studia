clear all;
close all;

a=double(imread('img.bmp'));for n=1:3;b(:,:,n)=kron(a(:,:,n),[1:2;2:3]==n)/255;end;

%imshow(b)
b = im2uint8(b);
%b = rgb2gray(b);
imshow(b)
%c = demosaic(b, 'grbg');
%imshow(c);