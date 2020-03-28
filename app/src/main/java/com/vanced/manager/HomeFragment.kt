package com.vanced.manager

import android.content.ComponentName
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.content.pm.PackageManager
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.include_changelogs.*
import com.vanced.manager.Adapter.SectionPageAdapter

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homefragment = inflater.inflate(R.layout.fragment_home, container, false)
        val viewPager = homefragment.findViewById(R.id.viewpager)
        val tabLayout = homefragment.findViewById(R.id.tablayout)
        return homefragment
    }

    private fun isMicrogInstalled(packageName: String, packageManager: PackageManager): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViewPager(viewpager)
        tablayout.setupWithViewPager(viewpager)
        tablayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setUpViewPager(viewPager2: ViewPager2) {
        val adapter = SectionPageAdapter(getChildFragmentManager())
        adapter.addFragment(VancedChangelogFragment, "Vanced")
        adapter.addFragment(MicrogChangelogFragment, "MicroG")
        viewpager.setAdapter(adapter)
    }

    companion object {
        val instance:HomeFragment
        get() {
            return HomeFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.home)

        super.onViewCreated(view, savedInstanceState)

        val builder = CustomTabsIntent.Builder()
        val pm = activity?.packageManager

        val bravebtn = getView()?.findViewById(R.id.brave_button) as Button
        val websitebtn = getView()?.findViewById(R.id.website_button) as Button
        val microgsettingsbtn = getView()?.findViewById(R.id.microg_settingsbtn) as Button
        val discordbtn = getView()?.findViewById(R.id.discordbtn) as Button
        val telegrambtn = getView()?.findViewById(R.id.tgbtn) as Button
        val twitterbtn = getView()?.findViewById(R.id.twitterbtn) as Button
        val redditbtn = getView()?.findViewById(R.id.redditbtn) as Button
        val git1btn = getView()?.findViewById(R.id.github_managerbtn) as Button
        val git2btn = getView()?.findViewById(R.id.github_botbtn) as Button
        val git3btn = getView()?.findViewById(R.id.github_websitebtn) as Button

        bravebtn.setOnClickListener {
            val braveurl = "https://brave.com/van874"
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.Brave))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(braveurl))
        }
        websitebtn.setOnClickListener {
            val vancedurl = "https://vanced.app"
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.Vanced))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(vancedurl))
        }
        microgsettingsbtn.setOnClickListener {
            val isInstalled = pm?.let { isMicrogInstalled("com.mgoogle.android.gms", it) }
            if (isInstalled == true) {
                val intent = Intent()
                intent.component = ComponentName(
                    "com.mgoogle.android.gms",
                    "org.microg.gms.ui.SettingsActivity"
                )
                startActivity(intent)
            } else {
                val toast = Toast.makeText(context, "Install MicroG First!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        discordbtn.setOnClickListener {
            val discordurl = "https://discord.gg/TUVd7rd"
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.Discord))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(discordurl))
        }
        telegrambtn.setOnClickListener {
            val telegramurl = "https://t.me/joinchat/AAAAAEHf-pi4jH1SDIAL4w"
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.Telegram))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(telegramurl))
        }
        twitterbtn.setOnClickListener {
            val twitterurl = "https://twitter.com/YTVanced"
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.Twitter))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(twitterurl))
        }
        redditbtn.setOnClickListener {
            val redditurl = "https://reddit.com/r/vanced"
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.Reddit))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(redditurl))
        }
        git1btn.setOnClickListener {
            val gitmanagerurl = "https://github.com/YTVanced/VancedInstaller"
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.GitHub))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(gitmanagerurl))
        }
        git2btn.setOnClickListener {
            val gitboturl = "https://github.com/YTVanced/VancedHelper"
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.GitHub))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(gitboturl))
        }
        git3btn.setOnClickListener {
            val gitwebsiteurl = "https://github.com/YTVanced/VancedWebsite"
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.GitHub))
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireContext(), Uri.parse(gitwebsiteurl))
        }


    }

}
