# Java-Music-Library
Java Music library project for the PA165 course

# Basic git rules (not only for this project)
1. Master branch is protected, therefore you cannot force-push into it. You must create pull request from your branch in order to push changes to master branch.
2. When adding, updating or deleting any project feature, create your own new branch with a name following rule 3.
3. Branches names
    * master - Production branch
    * feature/.. - Branch in which new feature is implemented/updated or a feature is removed.
    * fix/.. - Branch in which a known bug is fixed.
    
    Example: feature/album-tests (let's say you are going to create unit tests for album class)
4. Every commit should commit only those part of code, that create one logical component. It's better to have more small commits, than one bigger one.
5. When creating pull request, write a small description explaning what is the purpose of the pull request.
6. Always update master branch before checkouting new branch, so your branch is up to date with master.
7. Make sure to update your branch before creating pull request, so your branch can be easily merged into master.
8. 2 approving reviews are required in order to merge your branch into master branch.
    
# Assignment
Let us introduce you the Music Library used for managing songs. Each song has a title, bitrate, its position on the album's playlist, and commentary. For the sake of simplicity, each song belongs to exactly one musician, it is part of exactly one album, and can be of exactly one genre. Each album has basic attributes such as date of release, title, commentary, musician and album art. In order to support compilation albums each album can contain songs of different genres from different musicians.
