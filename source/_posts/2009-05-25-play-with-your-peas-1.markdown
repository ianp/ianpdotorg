---
author: ianp
date: '2009-05-25 00:32:05'
layout: post
slug: play-with-your-peas-1
status: publish
title: Play with your Peas 1
wordpress_id: '105'
categories:
- Flash
- Games
tags:
- Flash
- Games
- PushButtonEngine
---

So the project I'm using to learn Flash and [PushButtonEngine][01]
learning is [Dan Cook][02]'s [Play with your Peas][03] game prototype
challenge, I'll keep a log of my progress here. First things are to get
the libraries set up, this was pretty well covered in the last couple of
posts, but the actual steps I took (in brief) are: 1. checked out PBE
from subversion, it's hosted at
\`http://code.google.com/p/pushbuttonengine/\` and there are
instructions there on how to get it; 2. copied the 5 main projects from
\`pushbuttonengine/Projects/Engine\` into my workspace; 3. added
\`build.xml\` and \`build.properties\` files, and set up the
dependencies in them; 4. created a new project called
\`PlayWithYourPeas\` and set up the dependencies and build files; and
finally 5. set up a \`pbeproj\` file by copying an existing file and
modifying it. At this stage I'm almost ready to start cranking out some
code! But first, I need to copy all of the artwork that I'm going to
use, so I grabbed the zip file from [danc's site][03] and opened it up,
then created an \`Assets/Images\` folder and moved all of the images
apart from the mock-up to there. I also created \`Levels\` and
\`Sounds\` folders in my \`Assets\` folder, as I'll need them later on.
Now I can finally start writing code. For this project I'm going to
stick to plain ActionScript3 files: no Flex or MXML as I'd like to keep
the gradient of my learning curve down to a reasonable angle! Looking
over the [documentation][04] and sample projects I can see that there
are four main files that I need to create for my game: components,
levels, resources, and the main file which, in my case, is going to be
\`PlayWithYourPeas.as\`. I can start be just creating each file (\`⌘N →
AS Class...\` if you're using FDT, or however you normally create a new
ActionScript 3 class in your own set-up). I'll go through each file in
turn and explain what it's for and what I've put in it. \#\#\# Levels
The first class I'll look at is called \`Levels\`, in the \`Levels.as\`
file. Go ahead and create it now if your following along and haven't
already done so. The level information in PBE is all loaded from an XML
based level description file, but there needs to be something to hook
that into the rest of the game, and that's what this class is for. It
just has a single class with a constructor and the following code

~~~~ {lang="ActionScript" line="1"}
public class Levels {
   public function Levels() {
      LevelManager.Instance.AddLevelFileReference(
         "../Assets/Levels/level-01.pbelevel", 0);
      LevelManager.Instance.AddGroupReference("Everything", 0);
      LevelManager.Instance.Start();
   }
}
~~~~

The first line tells PBE where to find my level file (I'll describe it's
contents later on); the next line tells PBE whereabouts in the file to
start loading things from, and the last line starts the loading process.
The second line is important because you can have a huge amount of stuff
in your levels file and not everything needs to be loaded at the same
time, a single file can contain multiple game levels, allowing them to
share resources. Game levels are simple numbers as far as the engine is
concerned, so you add everything to your level description file and then
set up a group (see below) that contains just the relevant bits and
pieces needed for each actual game level. The second line says that
'level 0 uses all the things described in the group named "Everything"'.
One last thing to note about this file: the name \`level-01\` that I've
chosen for my level description file is a bit different from the
examples that are bundles with PBE; this is because I want to be able to
keep the initial download size for my game as small as possible and so
will want to break things up into different levels that can be loaded
asynchronously in the background - this way the player will get a fast
start-up and quick loading of new levels. This game will be too small
for this to matter, but I'd like to try to start off with good habits
then I don't need to change them later on! \#\#\# Resources This is just
a list of all of the resources (images, sounds, the level description,
and so on) that will be included in the game initially; i.e. will be
packaged up into the SWF or Air installer. As an aside, I'm going to
optimise this for the SWF version of the game, I'll handle bundling up
additional resources into the Air installer via the build scripts;
although, as with the levels file, for a game this small it makes no
difference. Initially I'm going to set up my resources so that they
include the level description file (it needs to be references here as
well) and the background image.

~~~~ {lang="ActionScript" line="1"}
public class Resources {
   [Embed(source="../Assets/Levels/level-01.pbelevel",
          mimeType='application/octet-stream')]
   private var _level:Class;
   [Embed(source="../Assets/Images/Background.png",
          mimeType='application/octet-stream')]
   private var _background:Class;
   public function Resources() {
      ResourceManager.Instance.RegisterEmbeddedResource(
         "../Assets/Levels/level-01.pbelevel",
         XMLResource,
         new _level() as ByteArray);
      ResourceManager.Instance.RegisterEmbeddedResource(
         "../Assets/Images/Background.png",
         ImageResource,
         new _background() as ByteArray);
   }
}
~~~~

The two private variables here basically hooks to hang the \`Embed\`
metadata from. Note that they both need to be octet streams, if you try
to use something more intuitive (say \`application/xml\` for the level
description and \`image/png\` for the background) it won't work, as the
PBE class that loads them basically just deals with things in terms of
raw byte arrays. The code in the constructor tells PBE how to handle the
data in the octet steams: \`XMLResource\` and \`ImageResource\` are
class names, the \`\_level\` class is created by the engine based on the
metadata that you pass in, so the \`new \_level()\` is just using the
\`Class\` as an object factory here. Since all of the use of these is
internal to the engine (we just tell it what to do, no actual code
required) this isn't a problem. Even so, hopefully in a future (i.e.
post 1.0) version of the engine it'll be possible to use smarter mime
types and have the resource loaders do some automatic conversion as a
result; it's be nice to be able to declare something of type
\`application/vnd.mycoolxsd+xml\` and have it accessible as an
ActionScript XML instance (via the E4X support in AS3). \#\#\# And
There's More... OK, this has run on for a wile now so I'm going to
finish this up in another post tomorrow, but to whet your appetite
here's a screenshot of what I've got so far:
![Screenshot](/images/2009/05/peas-01-bg-and-timer.png) Cool, huh? Well,
everybody's got to start somewhere, but it does demonstrate an import
feature: the timer in the top right hand corner is a custom component
that I've written and then added via the level description file, this is
neat as it's a standard component that I'll be able to reuse in other
games. I'll cover the other two files (components and the main game
file) as well as the custom component in my next post in this series...
[01]: http://pushbuttonengine.com/ [02]: http://lostgarden.com/ [03]:
http://lostgarden.com/2008/02/play-with-your-peas-game-prototyping.html
[04]: http://pushbuttonengine.com/docs/
