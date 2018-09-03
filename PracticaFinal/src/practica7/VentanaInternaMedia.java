/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.media.Media;

/**
 *
 * @author alberto
 */
public class VentanaInternaMedia extends javax.swing.JInternalFrame {

    private EmbeddedMediaPlayer vlcplayer = null;
    private MediaListPlayer mediaListPlayer;
    private VentanaPrincipal _parent = null;
    private File fMedia;
    private int cnt_lista = 0;
    private int total_elements = 0;
        
    private class VideoListener extends MediaPlayerEventAdapter {
        @Override
        public void finished(MediaPlayer mediaPlayer) {
            if (stopButton.isEnabled()) {
                stopButton.setEnabled(false);
            }
            if (!playButton.isEnabled()){
                playButton.setEnabled(true);
            }
            cnt_lista ++;
            if (cnt_lista == total_elements) {
                cnt_lista = 0;
            }
            mediaPlayer.playMedia(listaReproduccion.getItemAt(cnt_lista).getAbsolutePath());
            listaReproduccion.setSelectedIndex(cnt_lista);
        }
        @Override
        public void forward(MediaPlayer mediaPlayer) {
            cnt_lista++;
            if (cnt_lista == total_elements) {
                cnt_lista = 0;
            }
            mediaPlayer.playMedia(listaReproduccion.getItemAt(cnt_lista).getAbsolutePath());
        }
        @Override
        public void backward(MediaPlayer mediaPlayer) {
            cnt_lista--;
            if (cnt_lista < 0) {
                cnt_lista = total_elements-1;
            }
            mediaPlayer.playMedia(listaReproduccion.getItemAt(cnt_lista).getAbsolutePath());
        }
        @Override
        public void paused(MediaPlayer mediaPlayer) {
            pauseButton.setEnabled(false);
            if (!playButton.isEnabled()) {
                playButton.setEnabled(true);
            }
        }
        @Override
        public void playing(MediaPlayer mediaPlayer) {
            playButton.setEnabled(false);
            if(!pauseButton.isEnabled()) {
                pauseButton.setEnabled(true);
            }
            if(!stopButton.isEnabled()) {
                stopButton.setEnabled(true);
            }
        }
        @Override
        public void stopped(MediaPlayer mediaPlayer) {
            stopButton.setEnabled(false);
            if (!playButton.isEnabled()) {
                playButton.setEnabled(true);
            }
        }
        @Override
        public void timeChanged(MediaPlayer mediaPlayer, long pos) {
            
        }
        @Override
        public void positionChanged(MediaPlayer mediaPlayer, float pos) {
            float value = Math.min(pos * 100.0F, 100);
            progressbar.setValue((int)value);
        }
        @Override
        public void opening(MediaPlayer mediaPlayer) {
            /*if (vlcplayer!=null) {progressbar.setMaximum((int)vlcplayer.getLength());
                System.out.println(progressbar.getMaximum());
            }*/
        }
        public void muted(MediaPlayer mediaPlayer, boolean muted) {
            if (mediaPlayer.isMute()) {
                mediaPlayer.mute(muted);
                volume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/audiomute.png")));
            } else {
                mediaPlayer.mute(false);
                volume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/volumen.png")));
            }
        }
        
        public void volumeChanged(MediaPlayer mediaPlayer, int volumen) {
            if (!mediaPlayer.isMute()) {
                mediaPlayer.setVolume(volumen);
            } else {
                int v = mediaPlayer.getVolume();
                if (v < volumen) {
                    mediaPlayer.mute(false);
                    volume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/volumen.png")));
                    mediaPlayer.setVolume(v);
                } 
            }
            /*if (!this.vlcplayer.isMute()){
            this.vlcplayer.setVolume(this.volumeChange.getValue());
            } else {
            int volume = this.vlcplayer.getVolume();
            if (volume > this.volumeChange.getValue()) {
            this.vlcplayer.mute(false);
            this.volume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/volumen.png")));
            this.vlcplayer.setVolume(this.volumeChange.getValue());
            }
            }*/
        }
        @Override
        public void snapshotTaken(MediaPlayer mediaPlayer, String filename) {
            BufferedImage img = mediaPlayer.getSnapshot();
        }
    }
    /**
     * Creates new form VentanaInternaMedia
     */
    private VentanaInternaMedia(File f, VentanaPrincipal parent) {
        initComponents();
        this.volumeChange.setVisible(false);
        this.fMedia  = f;
        EmbeddedMediaPlayerComponent aVisual = 
                new EmbeddedMediaPlayerComponent();
        getContentPane().add(aVisual, BorderLayout.CENTER);
        // Dimension res = this.vlcplayer.getVideoDimension();
        // this.setSize(res);
        vlcplayer = aVisual.getMediaPlayer();
        EmbeddedMediaPlayerComponent aVisuala = new EmbeddedMediaPlayerComponent();
        vlcplayer.addMediaPlayerEventListener(new VideoListener());
        vlcplayer.setPlaySubItems(true);
        vlcplayer.setRepeat(true);
        File f1 = new File(f.getName()){
            @Override
            public String toString(){
                return this.getName();
            }
        };
        this.listaReproduccion.addItem(f1);
        total_elements++;
        this._parent = parent;
    }

    public static VentanaInternaMedia getInstance(File f, VentanaPrincipal parent) {
        VentanaInternaMedia v = new VentanaInternaMedia(f, parent);
        return (v.vlcplayer!=null?v:null);
    }
    
    public void play() {
        if (this.vlcplayer != null) {
            if (this.vlcplayer.isPlayable()) {
                this.vlcplayer.play();
            } else {
                vlcplayer.playMedia(fMedia.getAbsolutePath());
            }
        }
    }
    
    public void stop() {
        if (this.vlcplayer != null) {
            if (!this.vlcplayer.isPlaying()) {
                this.vlcplayer.pause();
            } else {
                this.vlcplayer.stop();
            }
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

        jToolBar1 = new javax.swing.JToolBar();
        playButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        progressbar = new javax.swing.JProgressBar();
        volume = new javax.swing.JButton();
        volumeChange = new javax.swing.JSlider();
        listaReproduccion = new javax.swing.JComboBox<>();
        sigSong = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        abrirMedia = new javax.swing.JMenu();
        abrirItem = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setMinimumSize(new java.awt.Dimension(274, 200));
        setPreferredSize(new java.awt.Dimension(600, 300));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jToolBar1.setRollover(true);

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Play.png"))); // NOI18N
        playButton.setFocusable(false);
        playButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(playButton);

        pauseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Pausa.png"))); // NOI18N
        pauseButton.setFocusable(false);
        pauseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pauseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(pauseButton);

        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Stop.png"))); // NOI18N
        stopButton.setFocusable(false);
        stopButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stopButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(stopButton);

        progressbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                progressbarMouseClicked(evt);
            }
        });
        jToolBar1.add(progressbar);

        volume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/volumen.png"))); // NOI18N
        volume.setFocusable(false);
        volume.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        volume.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        volume.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                volumeMouseMoved(evt);
            }
        });
        volume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volumeActionPerformed(evt);
            }
        });
        jToolBar1.add(volume);

        volumeChange.setValue(0);
        volumeChange.setPreferredSize(new java.awt.Dimension(100, 16));
        volumeChange.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volumeChangeStateChanged(evt);
            }
        });
        volumeChange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                volumeChangeMouseExited(evt);
            }
        });
        jToolBar1.add(volumeChange);

        listaReproduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaReproduccionActionPerformed(evt);
            }
        });
        jToolBar1.add(listaReproduccion);

        sigSong.setText("Sig");
        sigSong.setFocusable(false);
        sigSong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sigSong.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sigSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sigSongActionPerformed(evt);
            }
        });
        jToolBar1.add(sigSong);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_END);

        abrirMedia.setText("Archivo");

        abrirItem.setText("Abrir");
        abrirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirItemActionPerformed(evt);
            }
        });
        abrirMedia.add(abrirItem);

        jMenuBar1.add(abrirMedia);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        // TODO add your handling code here:
        File f = (File)this.listaReproduccion.getSelectedItem();
        if (f!=null) {
            this.vlcplayer.play();
        }
        this.play();
    }//GEN-LAST:event_playButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        // TODO add your handling code here:
        this.stop();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        this.vlcplayer.stop();
        this.vlcplayer = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        // TODO add your handling code here:
        if (this.vlcplayer.isPlaying()) {
                this.vlcplayer.pause();
        }
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void volumeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeMouseMoved
        // TODO add your handling code here:
        this.volumeChange.setVisible(true);
    }//GEN-LAST:event_volumeMouseMoved

    private void volumeChangeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumeChangeStateChanged
        // TODO add your handling code here:
        this.vlcplayer.setVolume(this.volumeChange.getValue());
        /*if (!this.vlcplayer.isMute()){
        this.vlcplayer.setVolume(this.volumeChange.getValue());
        } else {
        int volume = this.vlcplayer.getVolume();
        if (volume > this.volumeChange.getValue()) {
        this.vlcplayer.mute(false);
        this.volume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/volumen.png")));
        this.vlcplayer.setVolume(this.volumeChange.getValue());
        }
        }*/
    }//GEN-LAST:event_volumeChangeStateChanged

    private void volumeChangeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeChangeMouseExited
        // TODO add your handling code here:
        this.volumeChange.setVisible(false);
    }//GEN-LAST:event_volumeChangeMouseExited

    private void volumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volumeActionPerformed
        // TODO add your handling code here:
        if (!this.vlcplayer.mute()) {
            volume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/audiomute.png")));
            this.vlcplayer.mute(true);
        } else {
            volume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/volumen.png")));
            this.vlcplayer.mute(false);
        }
    }//GEN-LAST:event_volumeActionPerformed

    private void progressbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressbarMouseClicked
        // TODO add your handling code here:
        this.vlcplayer.setPosition(this.progressbar.getValue());
    }//GEN-LAST:event_progressbarMouseClicked

    private void abrirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirItemActionPerformed
        // TODO add your handling code here:
         JFileChooser dlg = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio", "mp3", "wma",
                            "aac","mp4", "wav","au", "aiff", "ogg","aac");
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Video", "avi", "mov", "asf",
                            "ogm", "mkv", "mpeg", "mp4", "ogg", "3gp", "mpg",
                            "wmw", "divx", "flv");
        dlg.setFileFilter(filter);
        dlg.setFileFilter(filter1);
        int resp = dlg.showOpenDialog(this);
        if( resp == JFileChooser.APPROVE_OPTION) {
            try{
                String name = dlg.getSelectedFile().toString();
                File f = new File(name){
                    @Override
                    public String toString(){
                        return this.getName();
                    }
                };
                this.listaReproduccion.addItem(f);
                total_elements++;
            }catch(Exception ex){
                System.err.println("Error al leer el archivo de audio");
            }
        }
    }//GEN-LAST:event_abrirItemActionPerformed

    private void listaReproduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaReproduccionActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_listaReproduccionActionPerformed

    private void sigSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sigSongActionPerformed
        // TODO add your handling code here:
        // vlcplayer.
        vlcplayer.setFullScreen(true);
    }//GEN-LAST:event_sigSongActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        this._parent.set_jlabel2(evt.getX(), evt.getY());
    }//GEN-LAST:event_formMouseMoved

    public BufferedImage screenshoot() {
        return this.vlcplayer.getSnapshot();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirItem;
    private javax.swing.JMenu abrirMedia;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox<File> listaReproduccion;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton playButton;
    private javax.swing.JProgressBar progressbar;
    private javax.swing.JButton sigSong;
    private javax.swing.JButton stopButton;
    private javax.swing.JButton volume;
    private javax.swing.JSlider volumeChange;
    // End of variables declaration//GEN-END:variables
}
