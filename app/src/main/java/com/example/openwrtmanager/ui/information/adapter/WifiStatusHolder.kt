package com.example.openwrtmanager.com.example.openwrtmanager.ui.information.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.model.InfoResponseModelItem
import com.example.openwrtmanager.databinding.ListItemWifiStatusBinding


//class WifiStatusHolder(private val binding: ListItemWifiStatusBinding) :
//    RecyclerView.ViewHolder(
//        binding.root
//    ) {
//    private val TAG = SystemInfoHolder::class.qualifiedName
//
//    @SuppressLint("SetTextI18n")
//    fun bind(wifiStatus: InfoResponseModelItem, wirelessDeviceData: InfoResponseModelItem) {
//        val _interfaces = mutableListOf<String>()
//        val view = mutableListOf<View>()
//        var ifnameToApData = mutableMapOf<String, Map<String, Any>>();
//
//        val hostHintData = (wifiStatus.result.get(1) as Map<*, *>)
//        var macToHostsMap = mutableMapOf<String, Any>();
////        Log.d("DEBUG", " wifiStatus: " + hostHintData);
//        for (mac in hostHintData.keys) {
//            try {
//                var maccc = hostHintData[mac] as Map<*, *>
//                if (maccc != null && maccc["name"] != null) {
//                    macToHostsMap.put(mac as String, maccc["name"]!!)
//                }
//            } catch (exception: Exception) {
//            }
//        }
//        val wirelessDeviceDataaaaaa = (wirelessDeviceData.result[1]) as Map<*, *>
//        if(wirelessDeviceDataaaaaa!=null){
//            for (radio in wirelessDeviceDataaaaaa.keys) {
//                var interfaces = (wirelessDeviceDataaaaaa[radio] as Map<*, *>)["interfaces"] as Map<*, *>;
//                for (interfaceee in interfaces) {
//                    var hihi = interfaceee as Map<*, *>
//                    ifnameToApData.put(
//                        hihi["ifname"].toString(), mutableMapOf(
//                            "ssid" to (hihi["iwinfo"] as Map<*,*>)["ssid"]!!,
//                            "noise" to (hihi["iwinfo"] as Map<*,*>)["noise"]!!,
//                            "signal" to (hihi["iwinfo"] as Map<*,*>)["signal"]!!,
//                            "channel" to (hihi["iwinfo"] as Map<*,*>)["channel"]!!,
//                            "bitrate" to (hihi["iwinfo"] as Map<*,*>)["bitrate"]!!,
//                            "frequency" to (hihi["iwinfo"] as Map<*,*>)["frequency"]!!,
//                            "encryption" to (hihi["config"] as Map<*,*>)["encryption"]!!,
//                            "mode" to (hihi["config"] as Map<*,*>)["mode"]!!,
//                        )
//                    )
//                }
//            }
//        }

//        try {
//            var wifiDeviceCounter = 0;
//            for ( interfaceee in data.skip(2)) {
//                var wifiInterface = widget.device.wifiDevices[wifiDeviceCounter];
//                wifiDeviceCounter++;
//                wifiInterfaces.add(wifiInterface);
//                var results = interfaceee[1]["results"];
//                for (var cli in results) {
//                    var i = cli;
//                    if (macToHostsMap[i["mac"]] != null)
//                        i["hostname"] = macToHostsMap[i["mac"]];
//                    i["ip"] = "";
//                    if (DataCache.macAddressMap.containsKey(i["mac"])) {
//                        var d = DataCache.macAddressMap[i["mac"]];
//                        i["ip"] = d.ipAddress;
//                    }
//                    i["ifname"] = wifiInterface;
//                    wifiData.add(i);
//                }
//            }
//        } catch (e, stackTrace) {
//            return generateErrorText(
//                e, stackTrace, "Error with WIFI data" + infoText);
//        }
//        var wirelessDeviceData = data[1]
//
//    }
//}