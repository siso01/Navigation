package com.navigation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val source = LatLng(23.217285116384726, 77.43712552396721)
    private val destination = LatLng(23.221845129995984, 77.43920017866584)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        addMarker(source, getString(R.string.source))
        addMarker(destination, getString(R.string.destination))
        drawPath()

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        val cameraPosition = CameraPosition.Builder()
                .target(source) // Sets the center of the map to Mountain View
                .zoom(17f)            // Sets the zoom
                .bearing(90f)         // Sets the orientation of the camera to east
                .tilt(30f)            // Sets the tilt of the camera to 30 degrees
                .build()              // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }

    private fun addMarker(markerLatLng: LatLng, marketName: String) {
        mMap.addMarker(MarkerOptions()
                .position(markerLatLng)
                .title(marketName))
    }

    private fun drawPath() {
        // Add polylines to the map.
        // Polylines are useful to show a route or some other connection between points.
        val polyline1 = mMap.addPolyline(PolylineOptions()
                .clickable(true)
                .add(
                        LatLng(23.217285116384726, 77.43712552396721),
                        LatLng(23.217389568737307, 77.43710225749491),
                        LatLng(23.217487735926646, 77.43756873876156),
                        LatLng(23.217991008404443, 77.43944637877661),
                        LatLng(23.21807231617653, 77.43970325512727),
                        LatLng(23.218127463419574, 77.43984401155983),
                        LatLng(23.2186845083253, 77.4395330482311),
                        LatLng(23.2190715776276, 77.4393463645393),
                        LatLng(23.22069235287274, 77.43845079969762),
                        LatLng(23.22152323828672, 77.43805560780609),
                        LatLng(23.22156793110645, 77.43815661488769),
                        LatLng(23.221575990027063, 77.43816415880383),
                        LatLng(23.221599588972502, 77.43826342351005),
                        LatLng(23.221741504931416, 77.43819241502379),
                        LatLng(23.222138825035103, 77.437985095834),
                        LatLng(23.222182998541395, 77.43801867033947),
                        LatLng(23.222210112461873, 77.43806762074118),
                        LatLng(23.222240923653736, 77.43817289767371),
                        LatLng(23.222276664874457, 77.4383023140758),
                        LatLng(23.22238573654613, 77.43873415051861),
                        LatLng(23.22238203910664, 77.43877103101418),
                        LatLng(23.22234999559913, 77.43880724023605),
                        LatLng(23.22224770193426, 77.43885619036725),
                        LatLng(23.222130002637964, 77.4388877060363),
                        LatLng(23.221838446384805, 77.43895110070346),
                        LatLng(23.221845129995984, 77.43920017866584)))


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(source, 4f))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.share -> {
                shareQRcode()
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareQRcode() {
        try {


            val intent: Intent = Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=23.217285116384726,77.43712552396721" +
                            "&daddr=23.221845129995984,77.43920017866584"))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show()
        }
    }
}