---
layout: post
title: "Category Counts in OctoPress Revisited"
date: 2012-10-30 18:41
comments: true
categories:
- Writing
- Command-Line Tips
---

As an improvement on an older shell script, here's a rake task to list all of the categories in your blog, along with post counts for each of them:

```ruby
desc "count the number of posts in each category"
task :count_categories do
  require 'yaml'

  counts = {}

  Dir.glob('source/_posts/*.markdown').each do |f|
    post = begin
      YAML.load(File.open(f))
    rescue ArgumentError => e
      puts "error: parsing #{f} - #{e.message}"
    end
    cats = post['categories']
    if cats.respond_to? "each"
      cats.each {|c| counts[c] = counts[c].to_i + 1}
    else
      counts[cats] = counts[cats].to_i + 1
    end
  end

  counts.sort_by {|k,v| v}.reverse.each {|k,v| puts "#{v} #{k}"}
end
```

Just add this to the end of your OctoPress Rakefile and you're good to go.
