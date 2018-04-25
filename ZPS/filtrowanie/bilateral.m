clear all;
close all;

S = 3;
D = 0.0001;

image = imread('imgnoise.jpg');
[sizex, sizey, color] = size(image);
figure('Name', 'Original'); imshow(image);

red_image = double(image(:,:,1));
green_image = double(image(:,:,2));
blue_image = double(image(:,:,3));

filtered_image_red = ones(sizex, sizex);
filtered_image_green = ones(sizex, sizex);
filtered_image_blue = ones(sizex, sizex);

diffYr = zeros(sizex, sizex);
diffYg = zeros(sizex, sizex);
diffYb = zeros(sizex, sizex);

for n = S + 1:sizex - S
   for m = S + 1:sizex - S
      diffYr(n, m) = sum(sum(abs(diff(red_image(n - S:n + S, m - S:m + S)))));
      szXY = max(0, S - floor(D * diffYr(n, m)));
      temp = (red_image(n - szXY:n + szXY, m - szXY:m + szXY));
      filtered_image_red(n, m) = mean(temp(:));
   end
end

for n = S + 1:sizex - S
   for m = S + 1:sizex - S
      diffYg(n, m) = sum(sum(abs(diff(green_image(n - S:n + S, m - S:m + S)))));
      szXY = max(0, S - floor(D * diffYg(n, m)));
      temp = (green_image(n - szXY:n + szXY, m - szXY:m + szXY));
      filtered_image_green(n, m) = mean(temp(:));
   end
end

for n = S + 1:sizex - S
   for m = S + 1:sizex - S
      diffYb(n, m) = sum(sum(abs(diff(blue_image(n - S:n + S, m - S:m + S)))));
      szXY = max(0, S - floor(D * diffYb(n, m)));
      temp = (blue_image(n - szXY:n + szXY, m - szXY:m + szXY));
      filtered_image_blue(n, m) = mean(temp(:));
   end
end

filtered_image = zeros(sizex, sizey, color);
filtered_image(:,:,1) = filtered_image_red;
filtered_image(:,:,2) = filtered_image_green;
filtered_image(:,:,3) = filtered_image_blue;

filtered_image = uint8(filtered_image);

imgnonoise = imread('img.jpg');
temp = filtered_image - imgnonoise;
temp = temp.*temp;
tempsum = sum(temp(:));
error = sqrt(tempsum / (sizex*sizey*3))

figure('Name', 'Filtered'); imshow(filtered_image);

A = image - filtered_image;
figure('Name', 'Diff'); imshow(A);
