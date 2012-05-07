---
layout: post
title: "RubyMotion and Interface Builder…"
date: 2012-05-07 19:24
comments: true
categories: 
- iOS
- Ruby
---

…sitting in a tree, K-I-S-S-I-N-G…

So, all of the cool kids have been talking about [Laurent Sansonetti][LS]'s next project: [RubyMotion][RM], a port of MacRuby targeted at iOS. On the whole I'm pretty impressed with what you can already do with it. One of the issues that people having been mentioning though, is that you lose the ability to use Interface Builder with it, but this isn't actually true!

Here I'm going to show you how to work with Interface Builder, I'll base the project off the one used in the Pragmatic Programmers [screencast][PP] (the icon and background images come from there as well, by the way).

All of the code for this project can be found on [GitHub][GH]. 

## Setting up a Project

Start off as normal: `motion create MagicBallDemo`. In the project folder create a new subfolder called *interfaces*, this is where all of the `.xib` files will be saved.

Next create a model class called `MagicBall` and a view controller called `MagicViewController` which extends `UIViewController`, also create a file to store some small helper methods called `helpers.rb`. Don't worry about the contents of these files for now, I'll come back to them, but you should have the following:

```sh
$ ls app
app_delegate.rb          magic_ball.rb
helpers.rb               magic_view_controller.rb
$ cat app/magic_view_controller.rb
class MagicViewController < UIViewController
end
```

The next thing to do is create the UI using interface builder.

## Creating the User Interface

Open Xcode and hit *⌘-N* to create a new file, select the *Empty* template and save the file into the folder you just created. Now add a `View Controller` from the object library and set the custom class to `MagicViewController`; you can't edit this field directly but you *can* paste into it, so select the class name from `app/magic_view_controller.rb`. 

There is a small issue with interface builder in that you can't add subviews to `UIImageViews`, which is a pain in the ass. A simple workaround is described in [this][SO] Stack Overflow question, and that's what we'll do here. So, add a `UIView` to the view controller and set it's custom class to be `UIImageView` (which should be available in the pick-list). You can't set the image here, that will need to be done in code.

Finally add the label and configure it as desired. Save your changes and close XCode.

You can compile this into a `.nib` using `ibtool`, this script will compile all of the interfaces in your project (just one in this case):

```sh
for i in interfaces/*.xix
do
  echo "compiling `basename $i`..."
  ibtool --compile resources/`basename -s .xib $i`.nib $i
done
```

## Connecting up the Code

The first thing we need to do is make sure our app delegate loads the interface from the nib that we've created, and we set our `@window` ivar from the loaded nib.

```objc
def application(application, didFinishLaunchingWithOptions:options)
  @window = UIWindow.alloc.initWithFrame(UIScreen.mainScreen.bounds)
  @window.rootViewController = NSBundle.mainBundle.loadNibNamed('MagicBallView', owner:self, options:nil).first
  @window.rootViewController.wantsFullScreenLayout = true
  @window.makeKeyAndVisible
  true
end
```

The nib loading process will create an instance of our view controllor for us, but we need to wire up a few connections add add some behaviour, here's a simplified version of the code:

```objc
  def viewDidLoad
    @magicBall = MagicBall.new
    @label = self.view.subviews.first

    view.image = UIImage.imageNamed('background.png')
    view.whenTapped do
      UIView.animateWithDuration(0.75,
        animations:lambda {
          @label.alpha = 0
          @label.transform = createTransform
        },
        completion:lambda { |finished|
          @label.text = @magicBall.answer
          @label.transform = CGAffineTransformIdentity
        })
    end
  end
```

I know that there is only 1 subview so it's easy to grab a reference to the label and store it in an ivar, a more realisic example could `select` the label based on a tag.

As mentioned above, I need to explicitly set the image and this happens here as well.

Finally, set up the gesture recognizer. This is one place that Ruby shines, it's trivial for us to add helper methods like this, have a look in `app/helpers.rb` for the code that enabled this.

## Wrapping Up

It's a good idea to add `resources/*.nib` to the `.gitignore` file as compiled resources don;t need to be committed to Git. I also add `doc/app` then I can use *rocco* to generate some documentation.

Take a look at the full [project on GitHub][GH] and let me know what you think!

[GH]: https://github.com/ianp/MagicBallDemo
[LS]: http://chopine.be/
[RM]: http://www.rubymotion.com/
[PP]: http://pragmaticstudio.com/screencasts/rubymotion
[SO]: http://stackoverflow.com/questions/2415561/apple-interface-builder-adding-subview-to-uiimageview
