% wavedemo
close all;
clear all;
prog = 47

%Q = -8.25
Q=-1.5
% haart2
% wavedec2 -> waverec2
N = 1;
wname = 'haar'

img = imread('noise.jpg');
imwrite(imread('clean.jpg')-img, 'diffclean.jpg');
[x y z] = size(img);
figure('Name', 'zaszumiony');
imshow(img);
% r_im = uint8(img(:,:,1));
% g_im = uint8(img(:,:,2));
% b_im = uint8(img(:,:,3));

r_im = double(img(:,:,1));
g_im = double(img(:,:,2));
b_im = double(img(:,:,3));
% transformata kosinusowa
% r_trans=dct2(r_im);
% g_trans=dct2(g_im);
% b_trans=dct2(b_im);

% transformata wlasha hadamada to nie dzia³a, dzia³a to na dole
% r_trans=fwht(fwht(r_im)');
% g_trans=fwht(fwht(g_im)');
% b_trans=fwht(fwht(b_im)');

% ten hadamard dzia³a
% lel_r=fwht(fwht(r_im)');
% lel_g=fwht(fwht(g_im)');
% lel_b=fwht(fwht(b_im)');

% W miare uniwersalna operacja
[r_trans,Sr] = wavedec2(r_im,N,wname);
[g_trans,Sg] = wavedec2(g_im,N,wname);
[b_trans,Sb] = wavedec2(b_im,N,wname);



%%%%%%%%%%% OPERATORY OCZYSZCZAJ¥CE

r_kwant = arrayfun(@(a) floor(a*2^Q + 0.5)/2^Q, r_trans);
g_kwant = arrayfun(@(a) floor(a*2^Q + 0.5)/2^Q, g_trans);
b_kwant = arrayfun(@(a) floor(a*2^Q + 0.5)/2^Q, b_trans);

r_trans(abs(r_trans)<prog)=0;
g_trans(abs(g_trans)<prog)=0;
g_trans(abs(b_trans)<prog)=0;

% Cr(abs(Cr)<prog) = 0;
% Cg(abs(Cg)<prog) = 0;
% Cb(abs(Cb)<prog) = 0;
% Sr(abs(Sr)<prog) = 0;
% Sg(abs(Sg)<prog) = 0;
% Sb(abs(Sb)<prog) = 0;



%%%%%%%%%%%%%%%%%%%%%%%OPERACJE ODWROTNE
% odwrotna kosinusowa
% r_ret_prog=idct2(r_trans);
% g_ret_prog=idct2(g_trans);
% b_ret_prog=idct2(b_trans);
% 
% r_ret_kwant=idct2(r_kwant);
% g_ret_kwant=idct2(g_kwant);
% b_ret_kwant=idct2(b_kwant);

% odwrotna wht
% r_ret_prog=ifwht(ifwht(r_trans)');
% g_ret_prog=ifwht(ifwht(g_trans)');
% b_ret_prog=ifwht(ifwht(b_trans)');
% 
% r_ret_kwant=ifwht(ifwht(r_kwant)');
% g_ret_kwant=ifwht(ifwht(g_kwant)');
% b_ret_kwant=ifwht(ifwht(b_kwant)');


% r_ret=ifwht(ifwht(r_trans)');
% g_ret=ifwht(ifwht(g_trans)');
% b_ret=ifwht(ifwht(b_trans)');

r_ret_prog = waverec2(r_trans, Sr, wname);
g_ret_prog = waverec2(g_trans, Sg, wname);
b_ret_prog = waverec2(b_trans, Sb, wname);

r_ret_kwant = waverec2(r_kwant, Sr, wname);
g_ret_kwant = waverec2(g_kwant, Sg, wname);
b_ret_kwant = waverec2(b_kwant, Sb, wname);


out_prog = zeros(x, y, 3);
out_prog(:,:,1) = r_ret_prog;
out_prog(:,:,2) = g_ret_prog;
out_prog(:,:,3) = b_ret_prog;
out_prog = uint8(out_prog);

out_kwant = zeros(x, y, 3);
out_kwant(:,:,1) = r_ret_kwant;
out_kwant(:,:,2) = g_ret_kwant;
out_kwant(:,:,3) = b_ret_kwant;
out_kwant = uint8(out_kwant);


figure('Name', 'progowany');
imshow(out_prog);
figure('Name', 'kwantowany');
imshow(out_kwant);
figure('Name', 'ró¿nica próg');
imshow(imread('clean.jpg')-out_prog);
figure('Name', 'ró¿nica kwant');
imshow(imread('clean.jpg')-out_kwant);

roznica_prog = imread('clean.jpg')-out_prog;
sums = roznica_prog.*roznica_prog;
sums = sum(sums(:));
error_prog = sqrt(sums/(x*y*3))

roznica_kwant = imread('clean.jpg')-out_kwant;
sums = roznica_kwant.*roznica_kwant;
sums = sum(sums(:));
error_kwant = sqrt(sums/(x*y*3))




% figure;
% imshow(out);
% figure;
% imshow(imread('clean.jpg')-out);
% figure;
% imshow(imread('noise.jpg')-out);
% kwantowanie/progowanie
% odwrotna
%w wyœwietl
%policz b³¹d
out = imread('clean.jpg')-imread('diffclean.jpg');
sums = out.*out;
sums = sum(sums(:));
error = sqrt(sums/(x*y*3))


