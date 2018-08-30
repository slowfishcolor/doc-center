# Doc Center Demo

Pull markdown from git repo, convert to html, upload to oss, then show as organized web pages.

## Menu Generate Algorithm

`CommonMark` have a `Node` object, which represent parsed markdown object. When parsing regulated `SUMMARY.md`, 
a `Node` instance looks like this: 

Markdown: 
```markdown
# Doc Template Show Case

* Introduction
    * [Introduction 1](/introduction/introduction_1.md)
    * [Introduction 2](/introduction/introduction_2.md)
* Version
    * [Version Summary](/version/version_summary.md)
    * Version Details
        * [Version Detail 1](/version/version-details/version_detail_1.md)
        * [Version Detail 2](/version/version-details/version_detail_2.md)
```

Node: 
```
HD: Heading <h1> <h2>...
BL: BulletList <ul>
LI: ListItem <li>
PR: Paragraph may be a <p> or just a placeholder
LK: Link <a>
TX: Text

                leaf      folder menu
Document - HD    |         |
         - BL - LI - PR - TX (Introduction) 
                   - BL - LI - PR - LK - TX (introduction_1.md)
                        - LI - PR - LK - TX (introduction_2.md)
              - LI - PR - TX (Version)
                   - BL - LI - PR - LK - TX (version_summary.md)
                        - LI - PR - TX (Version Details)
                             - BL - LI - PR - LK - TX (version_detail_1.md)
                                  - LI - PR - LK - TX (version_detail_2.md)
```

Menu:
```
Menu: name, children
Menu Node (MI): name, children, path; path point to a file, no path means just a folder
Menu Path (MP)
Menu Doc  (MD)

Menu(n,c) - MP (n: Introduction, c: 2)
                - MD (n: Introduction 1, p: introduction_1.md, c: 0)
                - MD (n: Introduction 2, p: introduction_2.md, c: 0)
            MP (n: Version, c: 2)
                - MD (n: Version Summary, p: version_summary, c: 0)
                - MP (n: Version Details, c: 2)
                    -MD (n: Version Detail 1, p: version_detail_1.md, c: 0)
                    -MD (n: Version Detail 2, p: version_detail_2.md, c: 0)
```

* Traversing by layer. 
* A `Text Leaf` (LI - PR - TX) will convert to a `Menu Path`. 
* A `Link Leaf` (LI - PR - LK - TX) will convert to a `Menu Doc`.   