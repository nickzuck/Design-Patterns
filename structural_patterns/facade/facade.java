/*
You are given a system which contains class relevant for Video Editing. These include BitEncoding, AudioMixing,
VideoScaling, Thumbnail, Publish. Your company wants to create a video creation software which uses AudioMixing,
Bitencoding and VideoScaling only. Design the system so that complexities of the software are hidden from outside world

Followup : The pro version of your software also include publishing directly from your software as well. How will you
make those changes in the new artifact of that software


Solution : Since the classes are already given and we wish to hide the complexities of the software, we would use facade
design pattern. This will also help us creating the pro version of the software for which we can create an additional
facade
 */

class VideoFile {
    private final String path; // file path
    VideoFile(String source) {
        this.path = source;
    }

    public String getPath(){
        return this.path;
    }
}

class BitEncoding {
    public static VideoFile encodeVideo(VideoFile file, String encoding){
        String fileSourcePath = file.getPath() ;
        String[] fileParts = fileSourcePath.split("\\.") ;
        String fileExt = fileParts[fileParts.length-1];
        String filePath = fileParts[0] ;

        String newFilePath = filePath + "_encoded."  + fileExt;
        System.out.println("Encoding video file");
        return new VideoFile(newFilePath) ;
    }
}


//NOTE :  Ideally we should return the new video file path from all the classes so that
// we can return the final video file path from whichever step we want
class AudioMixing {

    public static void mixAudio(String audioPath, VideoFile file){
        System.out.printf("Mixing audio from %s to video from %s\n", audioPath, file.getPath());
    }
}

class VideoScaling{
    public static void scaleVideo(int height, int width, VideoFile file){
        System.out.printf("Scaling video to %d height and %d width from path %s\n", height, width , file.getPath());
    }
}

class Thumbnail{
    public static void createThumnail(VideoFile file){
        System.out.printf("Creating thumbnail for %s\n", file.getPath());
    }
}

class VideoPublisher{
    public static void publishVideo(VideoFile file){
        System.out.printf("Publishing video : %s\n", file.getPath());
    }
}

// Facade for handling the complex subsystem
class VideoSoftwareCommunity {
    private VideoFile communityVideo ;
    private String audioPath, encoding ;
    VideoSoftwareCommunity(String path, String audioPath, String encoding){
        this.communityVideo = new VideoFile(path);
        this.audioPath = audioPath ;
        this.encoding = encoding ;
    }

    // We only need the functionality of Audiomixing, Bitencoding and VideoScaling
    public VideoFile transform(int height, int width){
        AudioMixing.mixAudio(audioPath, communityVideo);
        VideoScaling.scaleVideo(height, width, communityVideo);
        return BitEncoding.encodeVideo(communityVideo, encoding);
    }
}

// Additional facade
class VideoSoftwarePremium{
    private VideoFile communityVideo ;
    private String audioPath, encoding ;
    VideoSoftwarePremium(String path, String audioPath, String encoding){
        this.communityVideo = new VideoFile(path);
        this.audioPath = audioPath ;
        this.encoding = encoding ;
    }

    // We only need the functionality of Audiomixing, Bitencoding and VideoScaling
    public VideoFile transform(int height, int width){
        AudioMixing.mixAudio(audioPath, communityVideo);
        VideoScaling.scaleVideo(height, width, communityVideo);
        VideoFile encodedFile = BitEncoding.encodeVideo(communityVideo, encoding);
        Thumbnail.createThumnail(encodedFile);
        VideoPublisher.publishVideo(encodedFile);
        return encodedFile ;
    }
}


class facade {
    public static void main(String[] args){
        VideoSoftwareCommunity communityVideoSoftwareTest = new VideoSoftwareCommunity("communityVideo.mp4", "linkinPark.mp3", "4K") ;
        VideoFile finalVideo = communityVideoSoftwareTest.transform(1080, 720);
        System.out.printf("Final video file :%s\n\n", finalVideo.getPath());


        VideoSoftwarePremium premiumVideoSoftwareTest = new VideoSoftwarePremium("premiumVideo.mp4", "linkinPark.mp3", "4K") ;
        VideoFile finalVideoPremium = premiumVideoSoftwareTest.transform(1080, 720);
        System.out.println("Final video file :" + finalVideoPremium.getPath());
    }
}