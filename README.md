# OmniVirt Ad Network: Monetize your VR app with seamless experience

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

Now an Ad Space is ready. Next step is to enable the Ad on your application.


### Initialize a `VRAd` instance
 
First of all, create an `VRAd` instance with **AdSpace ID** provided by step above along with the callback listener.
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

### Callback

When the state of VRAd has been changed, `onAdStatusChanged` will be called with the new state in the parameter. There are different 5 states in total.

- **AdState.Loading** - Ad is being loaded in the background.

- **AdState.Ready** - Ad is ready to be shown. You can call `show()` function at this state to display the loaded ad.

- **AdState.Showing** - Ad is being displayed.

- **AdState.Completed** - Ad display is finished.

- **AdState.Failed** - Ad could not be loaded.


### Unload the VRAd

*`VRAd` will be unloaded automatically when ad is finished displaying.* But if you want to force closing the displaying ad before it finishes, you can call the following method.

```java
vrAd.unload();
```

# Questions?

If you have any question, please don't hesitate to email us at [contact@omnivirt.com](mailto:contact@omnivirt.com) !
