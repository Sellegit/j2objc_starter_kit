# J2objc Starter Kit

The Starter Kit is a great way to get started with [Sellegit/j2objc](https://github.com/Sellegit/j2objc). it includes a simple demo which walking you the basics of using the j2objc, and a project template which making creating a new project with j2objc easy.

## Install j2objc

First of all, you must have `j2objc`, a java to objectiveC transpiler. Get it from:

    $git clone git@github.com:Sellegit/j2objc.git

and follow the [instructions on building](https://github.com/google/j2objc/wiki/Building-J2ObjC)

Now you should have excutables `j2objc` and `j2objcc` under `/path_to/j2objc/dist/`. Add them to your search path. I personally prefer to use symlink. Assuming on Mac,

    $ln -s /path_to/j2objc/dist/j2objc /usr/bin/j2bjc
    $ln -s /path_to/j2objc/dist/j2objcc /usr/bin/j2objcc  

Test you do have those two under the search path by typing

    $j2objc

## Generate the demo

    $./starter_kit generate_demo

You can find the generated directory `j2objc_demo` in `generated/`.

You should replace the following `path_to` in the file `generated/j2objc_demo/lib/iosbinding/build.gradle`, and if `j2objc` has been installed, the `starter_kit` script will do it automatically:

    apply plugin: 'java'

    sourceSets {
        main.java.srcDirs += "/path_to/j2objc/runtime/src/main/java"
    }

    dependencies {
        compile files('/path_to/j2objc/dist/lib/j2objc_annotations.jar')
    }


To install dependencies,

    $cd generated/j2objc_demo
    $bundle; bundle install

Then we can go into the `generated/j2objc_demo/j2objc_demo` directory to make the project,

    $cd j2objc_demo 
    $make

don't worry if you see message from make that complains missing directories the first time you make the project, as they will be created by make later. Also, if the above complains 
> /bin/sh: integratej2objc: command not found

try

    $bundle exec make

If make successes, optionally you can enable incremental build by 

    #!replace $N with the number of processors you want for a concurrent build(e.g. -j8)
    $rerun -d .. -p "**/*.java" "make incremental -j$N" 

Now you have translated all the java sources into objectiveC sources, it's time to compile the project. This project uses Xcode.
Assuming you are under the project directory

    #! the starter_kit script will do this automatically, if j2objc has been installed.
    $echo "J2OBJC_HOME = /path_to/j2objc/dist;" > Env.xcconfig

    #! skip the next line if you have run it once
    $pod install

    $open j2objc_demo.xcworkspace

Now, try build and run the j2objc demo from Xcode.

## Create a new project

    $./starter_kit create_project com.example hello

It will generate the `hello` directory in `generated/`:

    generated 
    ├── hello 
    │   ├── Gemfile
    │   ├── README
    │   ├── build.gradle
    │   ├── create_project
    │   ├── hello                        // for xcode
    │   ├── hello.ios                    // writting ios app in java
    │   ├── hello.iml
    │   ├── hello.droid                  // just an android app
    │   ├── gradle
    │   ├── gradle.properties
    │   ├── gradlew
    │   ├── gradlew.bat
    │   ├── lib
    │   │   ├── iosbinding               // binding the runtime sources
    │   │   │   ├── build.gradle
    │   │   │   └── iosbinding.iml
    │   ├── local.properties
    │   └── settings.gradle

