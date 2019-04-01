package com.xitric.articleexercise;

public class Article {

    public static final Article[] articles = {new Article("Article 1", "Lorem ipsum dolor sit amet, lacus purus ante nibh tristique, vestibulum nisl. Integer ut pharetra vitae pellentesque, lectus lacus porro feugiat enim sodales non, et penatibus imperdiet urna, neque est pellentesque enim diam. Tortor tortor iste nisl est consectetuer, vel eu quis amet lacus. Sed eu. Libero suspendisse, nec orci metus fusce, consectetuer nec lacus ultrices pellentesque vitae justo, nunc pede. Praesent vitae. Proin fusce vestibulum ligula quisque."),
            new Article("Article 2", "Nisl vestibulum sit, amet dapibus sed erat, erat vehicula varius nibh quisque, libero vel ultrices eu elit fringilla, molestie fames sem. Nam sit ante sed praesent, volutpat eu consectetuer lacus, sociosqu sed eros neque vel auctor odio. Minim libero aliquam dictum. Et fusce in neque a, ut ipsum ante id quis id non, in nunc praesent possimus integer vitae. Feugiat lorem mattis commodo venenatis porta. Tincidunt vestibulum malesuada ipsum vestibulum. Laoreet mi aliquam mattis etiam ut, sed nonummy mauris turpis vestibulum mauris fusce, lorem vitae eros amet accumsan sit, pede ut neque sit a, est sed erat mauris justo. Arcu diam, sem sapien arcu nonummy, vehicula nulla quisque, nec pede aenean enim et, est vehicula ullamcorper nisl a in parturient. Leo tellus porttitor eu magna id nulla. Tortor mauris congue egestas nec mi vitae, iaculis sed ligula ligula cum sit, ac est felis, aliquam feugiat amet varius justo platea. Commodo arcu nulla varius netus ut."),
            new Article("Article 3", "Sed eget commodo tellus montes et nibh, ultrices curabitur sit donec nonummy, praesent sit nibh wisi proin eu, vulputate consectetuer accumsan maecenas, velit nonummy. Auctor tempus id consectetuer, arcu risus condimentum ac tincidunt habitant. Duis vivamus adipisicing feugiat auctor, ut fusce luctus ante mauris, eget libero, gravida quis orci eget, sed tempus justo. Ultricies facilisi sed metus amet. Tortor ac non sodales eget venenatis eu, consequat sed nulla rhoncus quam, et mi mattis maecenas feugiat ultrices amet, vulputate bibendum commodo nullam, in non sed. Id et, et nunc amet, convallis nulla morbi amet sit massa dolor, eget ullamcorper scelerisque. Id suspendisse lacus nunc nullam, urna nec sagittis consectetur, aenean imperdiet ultricies cras porttitor ipsum, arcu in et nibh lacus, dignissim commodo massa vestibulum quam eros."),
            new Article("Article 4", "Rutrum feugiat risus volutpat, diam ut nulla in, suspendisse et, libero tellus. Habitasse etiam, mattis aptent. Tempor curabitur libero habitasse nisl, ut non eu varius, at magna, luctus amet libero, elementum amet vivamus. Mi vel elit morbi cum a quis, augue felis pede aliquam. Erat natoque semper libero, vehicula ipsum, posuere ullamcorper fusce, mattis ante diam erat montes mi id. Mi aliquam sodales sed. Vitae curae felis, ante justo, nunc vulputate pellentesque eget. Turpis magnis vestibulum et ultrices tempor, diam natoque donec aliquam, conubia tortor eros velit sed. Ac consectetuer eleifend, neque nulla diam pulvinar, at pellentesque sagittis, sociis iaculis porttitor dis. In ipsum eget, pellentesque suspendisse nulla pede arcu nec, augue feugiat vestibulum, odio condimentum cursus, vitae quis vitae parturient pede et. Pellentesque elementum duis dui morbi a rutrum, erat egestas convallis.")};

    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
