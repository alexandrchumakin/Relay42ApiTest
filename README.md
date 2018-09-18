# Relay42ApiTest

Test automation approach using Selenium WebDriver, SikuliX and HttpClient.

## Prerequisites
- Java8 +
- Maven

#### To run tests in linux, you must install OpenCV 2.2+ and Tesseract 3, e.g.:
https://gist.github.com/Piyush3dB/89722db287be9984147a54d72cc9c73d
https://www.pyimagesearch.com/2017/07/03/installing-tesseract-for-ocr/

#### For some linux distributives you still might have errors during test runs, then you need to install CUDA, e.g.:
https://developer.nvidia.com/cuda-downloads?target_os=Linux&target_arch=x86_64&target_distro=Ubuntu&target_version=1604&target_type=deblocal

## To run tests
- Clone project
- Go to folder directory in terminal
- Execute command:
```sh
$ mvn test
```

#### If you have problems with Sikuli initialization, run a script inside project folder:
```sh
$ mvn install:install-file -Dfile=lib/sikulixsetup-1.1.3.jar -DgroupId=info.testing.automated.sikuli -DartifactId=sikulix -Dversion=1.1.3 -Dpackaging=jar
```
#### For running tests in Windows you might need to setup Sikuli using instruction:
http://sikulix.com/quickstart/
