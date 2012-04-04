---
author: ianp
date: '2004-10-03 05:10:58'
layout: post
slug: dynamic-classpaths-in-eclipse
status: publish
title: Dynamic Classpaths in Eclipse
wordpress_id: '46'
categories:
- Java
- Programming
- Eclipse
---

In Eclipse your plug-ins normally have their classpath’s based upon
their dependencies. I'm playing around with an RCP based application
that will need to reference external libraries to avoid onerous
licensing requirements. Here's the somewhat convoluted code required to
do this:

```java
Context getContext() {
    if (context != null) { return context; }
    final Properties props = new Properties();
    props.put(Context.INITIAL_CONTEXT_FACTORY, getInitialContextFactory());
    props.put(Context.PROVIDER_URL, getProviderURL());
    props.put(Context.SECURITY_PRINCIPAL, getDirectoryPrincipal());
    props.put(Context.SECURITY_CREDENTIALS, getDirectoryCredentials());
    URL[] classpath = null;
    Plugin plugin = JmsPlugin.getPlugin();
    try {
        String stringList = plugin.getPluginPreferences().getString(PreferencesInitializer.CLASS_PATH);
        StringTokenizer st = new StringTokenizer(stringList, File.pathSeparator);
        ArrayList v = new ArrayList();
        while (st.hasMoreElements()) {
            v.add(new URL("file://" + st.nextElement()));
        }
        classpath = (URL[]) v.toArray(new URL[v.size()]);
    } catch (MalformedURLException e) {
        String msg = e.getMessage();
        Debug.error(plugin, 0, msg != null ? msg : "", e);
        throw new RuntimeException(e);
    }
    ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
    ClassLoader newClassLoader = new URLClassLoader(classpath, getClass().getClassLoader());
    try {
        Thread.currentThread().setContextClassLoader(newClassLoader);
        context = new InitialDirContext(props);
    } catch (NamingException e) {
        String msg = e.getMessage();
        Debug.error(plugin, 0, msg != null ? msg : "", e);
        throw new RuntimeException(e);
    } finally {
        Thread.currentThread().setContextClassLoader(oldClassLoader);
    }
    return context;
}
```

The relevant code is in the first `try... catch` block. Then I can
have this plug-in rely on a second plug in which includes the JMS
interfaces only, and have a preference page which allows the user to
select a third party JMS provider by URL. At the moment it won't handle
JMS providers that reply on native methods, but that could be fixed the
same way if needed. The `Debug.error(...)` is just a utility class to
log an Eclipse `IStatus.ERROR` message. As an added benefit of this
approach, because I use the latest API version in my JMS plugin, I can
check for a JMS version at runtime using the metadata returned by
`Connection.getMetaData()` and then only call JMS 1.1 supplied methods
when they are available.
