im = imread('img.jpg');

%pois = poissrnd(double(im/2));
%pois2 = poissrnd(double(im/4));
%pois3 = poissrnd(double(im/8));
%pois4 = poissrnd(double(im/16));
%pois5 = poissrnd(double(im/32));
%pois6 = poissrnd(double(im/64));
%pois7 = poissrnd(double(im/128));
%[x, y, z] = size(pois7);
%images = [pois, pois2, pois3; pois4, pois5, pois6; ones(x, y, z)*255, pois7, ones(x, y, z)*255];
%imshow(images/255);

%sobel = edge(rgb2gray(pois), 'sobel');
%sobel2 = edge(rgb2gray(pois2), 'sobel');
%sobel3 = edge(rgb2gray(pois3), 'sobel');
%sobel4 = edge(rgb2gray(pois4), 'sobel');
%sobel5 = edge(rgb2gray(pois5), 'sobel');
%sobel6 = edge(rgb2gray(pois6), 'sobel');
%sobel7 = edge(rgb2gray(pois7), 'sobel');
%[xs, ys, zs] = size(sobel7);
%sobelimage = [sobel, sobel2, sobel3; sobel4, sobel5, sobel6; ones(xs, ys, zs)*255, sobel7, ones(xs, ys, zs)*255];
%imshow(sobelimage)

%canny = edge(rgb2gray(pois), 'canny');
%canny2 = edge(rgb2gray(pois2), 'canny');
%canny3 = edge(rgb2gray(pois3), 'canny');
%canny4 = edge(rgb2gray(pois4), 'canny');
%canny5 = edge(rgb2gray(pois5), 'canny');
%canny6 = edge(rgb2gray(pois6), 'canny');
%canny7 = edge(rgb2gray(pois7), 'canny');
%[xc, yc, zc] = size(canny7);
%cannyimage = [canny, canny2, canny3; canny4, canny5, canny6; ones(xs, ys, zs)*255, canny7, ones(xs, ys, zs)*255];
%imshow(cannyimage)

roberts = edge(rgb2gray(pois), 'roberts');
roberts2 = edge(rgb2gray(pois2), 'roberts');
roberts3 = edge(rgb2gray(pois3), 'roberts');
roberts4 = edge(rgb2gray(pois4), 'roberts');
roberts5 = edge(rgb2gray(pois5), 'roberts');
roberts6 = edge(rgb2gray(pois6), 'roberts');
roberts7 = edge(rgb2gray(pois7), 'roberts');
[xr, yr, zr] = size(roberts7);
robertsimage = [roberts, roberts2, roberts3; roberts4, roberts5, roberts6; ones(xs, ys, zs)*255, roberts7, ones(xs, ys, zs)*255];
imshow(robertsimage)