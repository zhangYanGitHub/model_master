
 # model_master
 #### 1.全局配置 config.build
 * 包括 开发模式和集成模式的切换
 * 第三方依赖库版本号统一管理
 
                      /**
                       *  true  开发模式 业务组件可以自由打包为apk
                       *  false  集成模式
                       */
                      isModel = false
                      isDebuggable = true //是否 可调试  日志开关
                      outputDir = "F:/buildDir"
                      LOG_DEBUG = isDebuggable.toString()
                      setting = [
                              applicatuionID   : "com.zhang.module",
                              appVersionCode   : 1,
                              appVersionName   : "1.0.0",
                              minSdkVersion    : 16,
                              targetSdkVersion : 26,
                              compileSdkVersion: 26,
                              buildToolsVersion: "26.0.2"
                      ]
                  
                      keyStore = [
                              keyAlias     : "zhangyan",
                              keyPassword  : "zhangyan",
                              storeFile    : "../key/fanlle.jks",
                              storePassword: "zhangyan"
                      ]
                      libVersion = [
                              support_v7_appcompat: "26.1.0",
                              support_v4_appcompat: "26.1.0",
                              support_design: "26.1.0",
                              test_junit          : "4.12",
                              test_runner         : "1.0.1",
                              test_espresso_core  : "3.0.1",
                              Picture_Selector    : "v2.1.7",
                              arouter_api         : "1.2.4",
                              arouter_compiler    : "1.0.4",
                              logger              : "2.1.1",
                              Glide               : "4.4.0",
                              constraint_layout   : "1.0.2"
                      ] 
   #### 2.在各个子module中根据isModel的值进行配置
   * app壳工程配置 
   
                apply plugin: 'com.android.application'
                
                def buildTime() {
                    return new Date().format("yyyy-MM-dd HH:mm:ss")
                }
                
                android {
                
                    signingConfigs {
                        release {
                            keyAlias rootProject.ext.keyStore.keyAlias
                            keyPassword rootProject.ext.keyStore.keyPassword
                            storeFile file(rootProject.ext.keyStore.storeFile)
                            storePassword rootProject.ext.keyStore.storePassword
                        }
                
                    }
                    buildToolsVersion rootProject.ext.setting.buildToolsVersion
                    compileSdkVersion rootProject.ext.setting.compileSdkVersion
                    defaultConfig {
                        applicationId rootProject.ext.setting.applicatuionID
                        minSdkVersion rootProject.ext.setting.minSdkVersion
                        targetSdkVersion rootProject.ext.setting.targetSdkVersion
                        versionCode rootProject.ext.setting.appVersionCode
                        versionName rootProject.ext.setting.appVersionName
                        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
                        buildConfigField "boolean", "LOG_DEBUG", rootProject.ext.LOG_DEBUG
                        multiDexEnabled true
                        //打包时间
                        resValue "string", "build_time", buildTime()
                        javaCompileOptions {
                            annotationProcessorOptions {
                                arguments = [moduleName: project.getName()]
                            }
                        }
                    }
                    buildTypes {
                        release {
                            //更改AndroidManifest.xml中预先定义好占位符信息
                            //manifestPlaceholders = [app_icon: "@drawable/icon"]
                            debuggable rootProject.ext.isDebuggable
                            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
                            zipAlignEnabled true
                            // 缩减resource文件
                            shrinkResources true
                            //Proguard
                            minifyEnabled true
                            //签名
                            signingConfig signingConfigs.release
                
                        }
                        debug {
                            debuggable rootProject.ext.isDebuggable
                            //签名
                            signingConfig signingConfigs.release
                        }
                
                    }
                    applicationVariants.all { variant ->
                        //这个修改输出的APK路径
                        if (variant.buildType.name != "debug") {//防止AS无法安装debug包(apk)
                            variant.getPackageApplication().outputDirectory = new File(rootProject.ext.outputDir)
                        }
                        variant.getPackageApplication().outputScope.apkDatas.forEach { apkData ->
                            //这个修改输出APK的文件名
                            apkData.outputFileName = "Model_" +
                                    variant.versionName + "_" +
                                    variant.flavorName + "_" +
                                    ".apk"
                        }
                    }
                    flavorDimensions "type"
                    productFlavors {
                        Test {
                            dimension "type"
                        }
                    }
                    dexOptions {
                        javaMaxHeapSize "4g"
                    }
                }
                dependencies {
                    implementation fileTree(include: ['*.jar'], dir: 'libs')
                    if (rootProject.ext.isModel) {
                        implementation project(':commolib')
                
                    } else {
                        implementation project(':main_model')
                        implementation project(':model_a')
                    }
                }
   * 业务组件main_model 的build.gradle  开发模式 可以打包独立apk  集成模式 作为壳app的library
   *  业务组件根据开发模式与否 动态配置 mainfest.xml 在这里有两个mainfest.xml module 文件下的 都有launchActivity application
   
      
                    
                     if (rootProject.ext.isModel) {
                         apply plugin: 'com.android.application'
                     } else {
                         apply plugin: 'com.android.library'
                     }
                     
                     android {
                     
                         buildToolsVersion rootProject.ext.setting.buildToolsVersion
                         compileSdkVersion rootProject.ext.setting.compileSdkVersion
                         signingConfigs {
                             release {
                                 keyAlias rootProject.ext.keyStore.keyAlias
                                 keyPassword rootProject.ext.keyStore.keyPassword
                                 storeFile file(rootProject.ext.keyStore.storeFile)
                                 storePassword rootProject.ext.keyStore.storePassword
                             }
                     
                         }
                         defaultConfig {
                             println "main_model ==========="
                             if(rootProject.ext.isModel){
                                 applicationId rootProject.ext.setting.applicatuionID
                                 //给applicationId添加后缀“.main_model”
                                 applicationIdSuffix ".main_model"
                             }
                             minSdkVersion rootProject.ext.setting.minSdkVersion
                             targetSdkVersion rootProject.ext.setting.targetSdkVersion
                             versionCode rootProject.ext.setting.versionCode
                             versionName rootProject.ext.setting.versionName
                             testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
                             javaCompileOptions {
                                 annotationProcessorOptions {
                                     arguments = [moduleName: project.getName()]
                                 }
                             }
                         }
                         sourceSets {
                             main {
                                 if (rootProject.ext.isModel) {
                                     manifest.srcFile 'src/main/module/AndroidManifest.xml'
                                 } else {
                                     manifest.srcFile 'src/main/AndroidManifest.xml'
                                     //集成开发模式下排除debug文件夹中的所有Java文件
                                     java {
                                         exclude 'com/zhang/debug/**'
                                     }
                                 }
                             }
                         }
                         buildTypes {
                             release {
                     
                     //            manifestPlaceholders = [package: rootProject.ext.setting.applicatuionID]
                                 minifyEnabled false
                                 proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
                                 if (rootProject.ext.isModel) {
                                     signingConfig signingConfigs.release
                                 }
                             }
                         }
                     
                     }
                     
                     dependencies {
                         implementation fileTree(dir: 'libs', include: ['*.jar'])
                         api project(':commolib')
                         annotationProcessor "com.alibaba:arouter-compiler:$rootProject.ext.libVersion.arouter_compiler"
                     }
#### commonlib 所有业务组件的依赖的公共组件  
* 项目中所有的依赖统一集中在这里 该库提供
* 1、commonlib组件的 AndroidManifest.xml 不是一张空表，这张表中声明了我们 Android应用用到的所有使用权限 uses-permission 和 uses-feature，放到这里是因为在组件开发模式下，所有业务组件就无需在自己的 AndroidManifest.xm 声明自己要用到的权限了。

* 2、commonlib组件的 build.gradle 需要统一依赖业务组件中用到的 第三方依赖库和jar包，例如我们用到的ActivityRouter、Okhttp等等。

* 3、commonlib组件中封装了Android应用的 Base类和网络请求工具、图片加载工具等等，公用的 widget控件也应该放在Common 组件中；业务组件中都用到的数据也应放于Common组件中，例如保存到 SharedPreferences 和 DataBase 中的登陆数据；

* 4、commonlib组件的资源文件中需要放置项目公用的 Drawable、layout、sting、dimen、color和style 等等，另外项目中的 Activity 主题必须定义在 Common中，方便和 BaseActivity 配合保持整个Android应用的界面风格统一。

####  参考博客：http://blog.csdn.net/guiying712/article/details/55213884  非常详细 可以看看
