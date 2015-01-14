package main;

import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.ControlP5;
import controlP5.Slider;
import pattern.ChladniTriangle;
import pattern.ChladniCircle;
import pattern.ChladniRectangle;
import pattern.ColorMode;
import processing.core.PApplet;

import java.awt.*;

/**
 * Created by mar on 15.12.14.
 */
public class ControlFrame extends PApplet {

    private ControlP5 cp5;
    private Main parent;
    private int w, h;

    public void setup () {
        size( w, h );
        cp5 = new ControlP5( this );
        cp5.addSlider( "rectN" ).setRange( 0, 40 ).setSize( 300, 20 ).setPosition( 10, 10 ).setValue( 2.0f );
        cp5.addSlider( "rectM" ).setRange( 0, 40 ).setSize( 300, 20 ).setPosition( 10, 50 ).setValue( 3.0f );

        float circleY = 120;
        cp5.addSlider( "circleN" ).setRange( 0, 40 ).setSize( 300, 20 ).setPosition( 10, circleY ).setValue( 2.0f );
        circleY += 40;
        cp5.addSlider( "circleM" ).setRange( 0, 40 ).setSize( 300, 20 ).setPosition( 10, circleY ).setValue( 3.0f );
        circleY += 40;
        cp5.addSlider( "circlePoles" ).setRange( 0, 40 ).setSize( 300, 20 ).setPosition( 10, circleY ).setValue( 33.0f );
        circleY += 40;
        cp5.addSlider( "circleScale" ).setRange( 0, 2 ).setSize( 300, 20 ).setPosition( 10, circleY ).setValue( 1.1f );

        float realCircleY = 300;
        cp5.addSlider( "realCircleN" ).setRange( 1, 20 ).setSize( 300, 20 ).setPosition( 10, realCircleY ).setValue( 2.0f );
        realCircleY += 40;
        cp5.addSlider( "realCircleM" ).setRange( 1, 14 ).setSize( 300, 20 ).setPosition( 10, realCircleY ).setValue( 3.0f );

        float generalY = 450;
        cp5.addSlider( "jumpyness" ).setRange( 0, 200 ).setSize( 300, 20 ).setPosition( 10, generalY ).setValue( 30.0f );
        generalY += 30;
        cp5.addSlider( "particleSize" ).setRange( 0, 30 ).setSize( 300, 20 ).setPosition( 10, generalY ).setValue( 3.0f );
        generalY += 30;
        cp5.addSlider( "particleCount" ).setRange( 0, 80000 ).setSize( 300, 20 ).setPosition( 10, generalY ).setValue( 10000.0f );
        generalY += 30;

        Slider particleOpacitySliderRect = cp5.addSlider( "particleOpacityRect" ).setRange( 0, 1 ).setSize( 100, 20 ).setPosition( 10, generalY ).setValue( 0.6f ).addListener( new ControlListener( ) {
            @Override
            public void controlEvent ( ControlEvent controlEvent ) {
                parent.chladniRect.setParticleOpacity( controlEvent.getValue() );
            }
        } );

        Slider particleOpacitySliderTriangle = cp5.addSlider( "particleOpacityTriangle" ).setRange( 0, 1 ).setSize( 100, 20 ).setPosition( 130, generalY ).setValue( 0.6f ).addListener( new ControlListener( ) {
            @Override
            public void controlEvent ( ControlEvent controlEvent ) {
                parent.chladniTriangle.setParticleOpacity( controlEvent.getValue() );
            }
        } );

        Slider particleOpacitySliderCircle = cp5.addSlider( "particleOpacityCircle" ).setRange( 0, 1 ).setSize( 100, 20 ).setPosition( 250, generalY ).setValue( 0.6f ).addListener( new ControlListener( ) {
            @Override
            public void controlEvent ( ControlEvent controlEvent ) {
                parent.chladniCircle.setParticleOpacity( controlEvent.getValue() );
            }
        } );
        generalY += 40;



        cp5.addToggle( "thresholdToggle" )
                .setPosition( 10, generalY )
                .setSize( 50, 20 )
                .setValue( false )
                .setMode( ControlP5.SWITCH )
        ;

        cp5.addToggle( "colorModeToggle" )
                .setPosition( 80, generalY )
                .setSize( 50, 20 )
                .setValue( false )
                .setMode( ControlP5.SWITCH )
        ;

        cp5.addToggle( "bloomToggle" )
                .setPosition( 150, generalY )
                .setSize( 50, 20 )
                .setValue( false )
                .setMode( ControlP5.SWITCH )
        ;

        cp5.addToggle( "original" )
                .setPosition( 220, generalY )
                .setSize( 50, 20 )
                .setValue( false )
                .setMode( ControlP5.SWITCH )
        ;

        cp5.addToggle( "motionBlur" )
                .setPosition( 290, generalY )
                .setSize( 50, 20 )
                .setValue( false )
                .setMode( ControlP5.SWITCH )
        ;
        generalY += 50;
        cp5.addSlider( "threshold" ).setRange( 0, 1 ).setSize( 300, 20 ).setPosition( 10, generalY ).setValue( 0.2f );

        cp5.loadProperties();
    }

    public void rectM ( float val ) {
        parent.chladniRect.frequencyChanged( );
        ChladniRectangle r = ( ChladniRectangle ) parent.chladniRect.getSurface( );
        r.setM( val );
    }

    public void rectN ( float val ) {
        parent.chladniRect.frequencyChanged( );
        ChladniRectangle r = ( ChladniRectangle ) parent.chladniRect.getSurface( );
        r.setN( val );
    }

    public void circleN ( float val ) {
        parent.chladniTriangle.frequencyChanged( );
        ChladniTriangle c = ( ChladniTriangle ) parent.chladniTriangle.getSurface( );
        c.setN( val );
    }

    public void circleM ( float val ) {
        parent.chladniTriangle.frequencyChanged( );
        ChladniTriangle c = ( ChladniTriangle ) parent.chladniTriangle.getSurface( );
        c.setM( val );
    }

    public void circlePoles ( float val ) {
        parent.chladniTriangle.frequencyChanged( );
        ChladniTriangle c = ( ChladniTriangle ) parent.chladniTriangle.getSurface( );
        c.setPoles( ( int ) ( val ) );
    }

    public void circleScale ( float val ) {
        parent.chladniTriangle.frequencyChanged( );
        ChladniTriangle c = ( ChladniTriangle ) parent.chladniTriangle.getSurface( );
        c.setScale( val );
    }

    public void realCircleN ( float n ) {
        parent.chladniCircle.frequencyChanged( );
        ChladniCircle realCircle = ( ChladniCircle ) parent.chladniCircle.getSurface( );
        realCircle.setN( n );
    }

    public void realCircleM ( float m ) {
        parent.chladniCircle.frequencyChanged( );
        ChladniCircle realCircle = ( ChladniCircle ) parent.chladniCircle.getSurface( );
        realCircle.setM( m );
    }

    public void jumpyness ( float _jumpyness ) {
        parent.chladniRect.setRebuildSpeed( _jumpyness );
        parent.chladniTriangle.setRebuildSpeed( _jumpyness );
        parent.chladniCircle.setRebuildSpeed( _jumpyness );
    }

    public void threshold ( float _threshold ) {
        parent.getMetaBallModifier( ).setThreshold( _threshold );
    }

    public void thresholdToggle ( boolean isEnabled ) {
        parent.getMetaBallModifier( ).setEnabled( isEnabled );
    }

    public void colorModeToggle ( boolean isEnabled ) {
        /*
        if ( isEnabled ) {
            parent.chladniRect.setColorMode( ColorMode.MONOCHROME );
            parent.chladniTriangle.setColorMode( ColorMode.MONOCHROME );
            parent.chladniCircle.setColorMode( ColorMode.MONOCHROME );
        } else {
            parent.chladniRect.setColorMode( ColorMode.VELOCITIES );
            parent.chladniTriangle.setColorMode( ColorMode.VELOCITIES );
            parent.chladniCircle.setColorMode( ColorMode.VELOCITIES );
        }
        */
    }

    public void bloomToggle ( boolean isEnabled ) {
        parent.getBloomModifier( ).setEnabled( isEnabled );
    }

    public void original ( boolean isEnabled ) {
        parent.drawSurface = isEnabled;
    }

    public void motionBlur( boolean isEnabled ) {
        parent.doMotionBlur = isEnabled;
    }

    public void particleSize ( float _particleSize ) {
        parent.chladniRect.setParticleSize( _particleSize );
        parent.chladniTriangle.setParticleSize( _particleSize );
        parent.chladniCircle.setParticleSize( _particleSize );
    }

    public void particleCount ( float _particleCount ) {
        parent.chladniTriangle.setParticleCount( ( int ) ( _particleCount ) );
        parent.chladniRect.setParticleCount( ( int ) ( _particleCount ) );
        parent.chladniCircle.setParticleCount( ( int ) ( _particleCount ) );
    }


    public void draw () {
        background( 0 );
    }

    public ControlFrame ( Main theParent, int theWidth, int theHeight ) {
        parent = theParent;
        w = theWidth;
        h = theHeight;
    }

    public void saveParameters() {
        this.cp5.saveProperties();
    }

    public static ControlFrame addControlFrame ( Main pa, String theName, int theWidth, int theHeight ) {
        Frame f = new Frame( theName );
        ControlFrame p = new ControlFrame( pa, theWidth, theHeight );
        f.add( p );
        p.init( );
        f.setTitle( theName );
        f.setSize( p.w, p.h );
        f.setUndecorated( true );
        f.setLocation( 0, 150 );
        f.setResizable( false );
        f.setVisible( true );
        return p;
    }
}