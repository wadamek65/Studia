close all;
clear all;

I = imread('wiosna.tiff');
img = rgb2ycbcr(I);
[x y z] = size(img);
I2 = zeros([x y z]);
for i=1:1:3
    I = img(:,:,i);
    I = im2double(I);
    T = dctmtx(8);
    dct = @(block_struct) T * block_struct.data * T';
    B = blockproc(I,[8 8],dct);
    mask = [1   1   1   1   0   0   0   0
            1   1   1   0   0   0   0   0
            1   1   0   0   0   0   0   0
            1   0   0   0   0   0   0   0
            0   0   0   0   0   0   0   0
            0   0   0   0   0   0   0   0
            0   0   0   0   0   0   0   0
            0   0   0   0   0   0   0   0];
    %mask = [16 11 10 16 24 40 51 61;
    %        12 12 14 19 26 58 60 55;
    %        14 13 16 24 40 57 69 56;
    %        14 17 22 29 51 87 80 62;
    %        18 22 37 56 68 109 103 77;
    %        24 35 55 64 81 104 113 92;
    %        49 64 78 87 103 121 120 101;
    %        72 92 95 98 112 100 103 99;
    %        ];
    B2 = blockproc(B,[8 8],@(block_struct) mask .* block_struct.data);
    I2(:,:,i) = B2;
end
max_val = intmax('int8');
divider = max_val / max(I2(:));
I2 = I2*double(divider);
I2 = int16(I2);
save('test.jwa', 'I2', 'divider');