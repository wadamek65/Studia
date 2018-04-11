clear all
close all
im = imread('pas2.jpg');
im2 = imresize(im, 0.1);
im2 = imresize(im2, 10);
imshow(im2)