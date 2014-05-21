package paintbasico2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author EDUARDO
 */
public class Lienzo extends javax.swing.JPanel {

    /**
     * Creates new form Lienzo
     */
    Shape s;
    static boolean relleno;
    static int forma = 0;
    static  boolean editar;
    static Stroke sk = new BasicStroke(1.0f);
    static  Color color = new Color(0,0,0);
    List<Shape> vShape = new ArrayList<Shape>();
    private BufferedImage img;
    Point2D p1 = new Point(0,0);
    Point2D pfin;
    
    public Lienzo() {
        initComponents();
    }
    public void paint(Graphics g){ 
        super.paint(g); 
        Graphics2D g2d = (Graphics2D)g; 
        g2d.setPaint(color); 
        g2d.setStroke(sk);
        
        if(img != null)
            g2d.drawImage(img,0,0,this);
        
         for(Shape s: vShape) { 
             if(relleno) g2d.fill(s);
                    g2d.draw(s); 
        } 
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        this.pfin = evt.getPoint();
        if (editar)
        {
            s = getSelectedShape(evt.getPoint());
            if (s != null)
            {
                double x = (s instanceof Line2D) ? ((Line2D)s).getX1() : s.getBounds2D().getX();
                double y = (s instanceof Line2D) ? ((Line2D)s).getY1() : s.getBounds2D().getY();
                p1.setLocation(x - pfin.getX(), y - pfin.getY());
            }
        }
        else
        {
            this.vShape.add(createShape(forma, pfin, pfin));
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        Point p = evt.getPoint();
        
        if(editar){
              if(s !=null)
                setLocation(s, new Point2D.Double(p.getX()+ p1.getX() , p.getY()+ p1.getY()));
        }else{
            updateShape(forma, pfin , p);
        }
        repaint();
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        Point p = evt.getPoint();
        
        if(editar){
            if(s !=null)
                setLocation(s, new Point2D.Double(p.getX()+ p1.getX() , p.getY()+ p1.getY()));
        }
        else{
            updateShape(forma, pfin , p);
        }
        repaint();
    }//GEN-LAST:event_formMouseDragged

     public static  void setForma(int n)
    {
        forma=n;    
    }
     
     public void setImage(BufferedImage bi)
     {
         img = bi;
         this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
     }
     
     public BufferedImage getImage()
     {
         return img;
     }
     
     public BufferedImage pintarImagen(BufferedImage img)
     {
         Graphics2D g2d = img.createGraphics();
         g2d.setPaint(color); 
         g2d.setStroke(sk);
         
         for(Shape s: vShape) { 
             if(relleno) g2d.fill(s);
                    g2d.draw(s); 
        }
         
         return img;
         
     }

    public static  void setRelleno(boolean b)
    {
        relleno = b;
    }
 
    public static  void setColor(Color c)
    {
        color = c;
    }
  
    public static void setStroke(Stroke s)
    {
        sk = s;
    }
   
    public static void setEditar(boolean ed)
    {
        editar = ed;
    }
   
    private Shape createShape(int forma,Point2D p1, Point2D p2)
    {
        if((p1 == null) || (p2 == null) && (forma != 0) ) return null;
        switch(forma)
        {
            case 0:
                return s = new Line2D.Double(p1, p1);
            case 1:
                return s = new Line2D.Double(p1,p2);
            case 2:
                s = new Rectangle2D.Double();
                ((RectangularShape)s).setFrameFromDiagonal(p1, p2);
                return s;
            case 3:
                s = new Ellipse2D.Double();
                ((RectangularShape)s).setFrameFromDiagonal(p1, p2);
                return s;
        }  
        return s = null;
    }
    
    private void updateShape(int forma,Point2D p1, Point2D p2)
    {
        if(p1 == null || (p2 == null) && (forma != 0) ) return;
        switch(forma)
        {
            case 0:
                ((Line2D)s).setLine(p1, p1);
                break;
            case 1:
               ((Line2D)s).setLine(p1, p2);
                break;
            case 2:
            case 3:
                ((RectangularShape)this.s).setFrameFromDiagonal(p1, p2);
                break;
        }
    }
     private boolean isNear(Line2D line, Point2D p)
    {          
         return line.ptLineDist(p)<=2.0D; 
    } 
    
     private boolean contains(Shape s,Point2D p)
     {
         if((s instanceof Line2D))
                return isNear((Line2D)s,p);
                
         return s.contains(p);
     }
     
    private Shape getSelectedShape(Point2D p){ 
        for(ListIterator<Shape> it= vShape.listIterator(vShape.size()); it.hasPrevious();)
        {    
            if(contains((s = (Shape)it.previous()),p)) 
                        return s; 
        }
        
            return null; 
    }
    
    private void setLiLocation(Line2D line, Point2D pos){  
         double dx=pos.getX()-line.getX1(); 
         double dy=pos.getY()-line.getY1();  
         Point2D newp2 = new Point2D.Double(line.getX2()+dx,line.getY2()+dy);    
         line.setLine(pos,newp2); 
    }
    
    private void setLocation(Shape s, Point2D p)
    {
        if((s instanceof Line2D))
            setLiLocation((Line2D)s, p);
        if((s instanceof RectangularShape)){
            RectangularShape rs = (RectangularShape)s;
            rs.setFrame(p, new Dimension((int)rs.getWidth(),(int)rs.getHeight()));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
