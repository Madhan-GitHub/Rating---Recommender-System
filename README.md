# CS532: Data Mining - Project
## Name: Madhan Thangavel
## B NO: B00814916

-----------------------------------------------------------------------
-----------------------------------------------------------------------
## Environment

Implemented in vs code and Works in remote.cs.binghamton.
-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in recommenderSystem/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile recommenderSystem/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

Note : Ensure you are in Rating---Recommender-System folder (before recommenderSystem/src/build.xml)
       unzip 
       cd Rating---Recommender-System

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile recommenderSystem/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

Note : Ensure you are in Rating---Recommender-System folder (before recommenderSystem/src/build.xml)
       unzip 
       cd Rating---Recommender-System

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile recommenderSystem/src/build.xml run -Dinput="train.txt" -Doutput="output.txt"

Note: Arguments accept the absolute path of the files.

      Ensure you are in Rating---Recommender-System folder (before recommenderSystem/src/build.xml)
       unzip 
       cd Rating---Recommender-System

-----------------------------------------------------------------------
## Description:

It is Recommender System written in Java program to process a input file with set of matrix to determine user - Item rating based on predictive algorithm.

The system is able to predict the rating value k of user i for item j if user i has not yet given such a rating. In other words, given such a partially filled out matrix, a recommender system shall be able to fill out all the predicted rating values for those elements.

-----------------------------------------------------------------------
## References:

	https://en.wikipedia.org/wiki/Recommender_system
	http://infolab.stanford.edu/~ullman/mmds/ch9.pdf
	http://www.cs.carleton.edu/cs_comps/0607/recommend/recommender/itembased.html

    [1] G. Guo, J. Zhang, Z. Sun and N. Yorke-Smith, LibRec: A Java Library for Recommender Systems, in Posters, Demos, Late-breaking Results and Workshop Proceedings of the 23rd Conference on User Modelling, Adaptation and Personalization (UMAP), 2015.

    [2] J. Breese, D.. Heckerman, and C. Kadie, Empirical analysis of predictive algorithms for collaborative filtering, Proc. Conf. Uncertainty in Artificial Intelligence, (UAI98) 1998

    [3] J.L. Herlocker, J.A. Konstan, J.R.A. Borchers, and J. Riedl, An algorithmic framework for performing collaborative filtering, Proc. International on ACM SIGIR Research and Development in Information Retrieval, (SIGIR98) 1998

    [4] P. Resnick and H.R. Varian, Recommender Systems, Special Issue of Communications of the ACM, 40(3), 1997


-----------------------------------------------------------------------

Date: [22 NOVEMBER 2020]