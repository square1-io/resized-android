
[ ![Download](https://api.bintray.com/packages/square1io/maven/resized/images/download.svg) ](https://bintray.com/square1io/maven/resized/_latestVersion)

# Resized-Android

This is the Android client for resized.co, a realtime image resize manipulation service.

## Install

Via Gradle

```gradle
repositories {
  jcenter() 
}

dependencies {
  compile 'io.square1:resized:1.0.1'
}
```

## Usage

``` 
    //Initialize and authenticate
    Resized resized = Resized.init(key ,secret);

    //Override host if applicable (optional step)
    resized.setHost("https://img.resized.co");

    //Set the default failover image ( optional step)
    resized.setDefaultImage("http:/www.example.com/no-image.jpg");

    //Process image resize with the parameters: (url, width, height)
    String img = resized.process("http://www.example.com/some-image.jpg", 100, 100);
```


## License

The MIT License (MIT). Please see [License File](LICENSE.md) for more information.



