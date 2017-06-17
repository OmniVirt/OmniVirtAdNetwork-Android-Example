# OmniVirt Ad Network: Monetize your VR App with Seamless Experience

![Screenshot](https://github.com/OmniVirt/OmniVirtAdNetwork-Android-Example/blob/master/screenshotad.jpg?raw=true)

**OmniVirt Ad Network** provides you ***an advertising platform*** enables developers and publishers to monetize their apps with engaging VR content in seamless user experience way.

Simply integrate the OmniVirt SDK into your iOS, Android or Web application and get paid for presenting sponsored 360° video experiences to your users. Backfill your inventory with premium CPM experiences from OmniVirt’s network of advertisers. We support both 360° and 2D video ads inside VR apps.

Visit [omnivirt.com](https://omnivirt.com/) to create ad space to start monetizing. Contact us for more info at [contact@omnivirt.com](mailto:contact@omnivirt.com).

## Add the OmniVirt SDK to your app
 
Add the following lines to `build.gradle` of your application module.
```
dependencies {
    compile 'com.omnivirt:omnivirt-android-sdk:0.10.2'
} 
 
repositories {
    maven {
        url 'https://dl.bintray.com/omnivirt/OmniVirtSDK'
    }
}
```

## Usage

**OmniVirt Ad Network** can be integrated into your Android application in just few easy steps.

### Get Started

1. **Sign up** for an account at [OmniVirt](www.omnivirt.com)
2. **Create one or more Ad Spaces** for your app (for each Ad Space you can select different content and will get separate reporting)
3. Keep the **AdSpace ID** assigned for further use.

Now the ad space is ready. Next step is to enable ad on your application.


### Initialize a `VRAd` instance
 
First of all, create an `VRAd` instance with **AdSpace ID** provided by step above.
```java
public class MainActivity extends AppCompatActivity {
    VRAd vrAd;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        vrAd = new VRAd(ADSPACE_ID, new OnVRAdInteractionListener() {
            @Override
            public void onAdStatusChanged(VRAd vrAd, AdState adState) {
                // Handle the Ad Status Changed here
            }
        });
        vrAd.load();
    }

}
```

Ad will be loaded in the background and once it is ready, `onAdStatusChanged` will be called with `Ready` state. If you want ad to start playing automatically, just add the following code snippet to the callback function.

```java
  ...
  @Override
  public void onAdStatusChanged(VRAd vrAd, AdState adState) {
    if (adState == AdState.Ready) {
      vrAd.show();
    }
  }
  ...
```

And it's all ... done !

# Questions?

Please email us at [contact@omnivirt.com](mailto:contact@omnivirt.com)
