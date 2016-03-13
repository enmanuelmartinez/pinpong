import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Created by enmanuel on 13/03/16.
 */
public class Stage {

    ArrayList<Node> nodes;

    public void Stage(){
        nodes = new ArrayList<Node>();
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void addNode(int index, Node node){
        nodes.add(index, node);
    }

    public Boolean removeNode(Node node){
        return nodes.remove(node);
    }

    public Node removeNode(int index){
        return nodes.remove(index);
    }

    public void update(){
        for(Node node: nodes){
            node.update();
        }
    }

    public void draw(final Graphics2D g2){
        for(Node node: nodes){
            node.draw(g2);
        }
    }

}